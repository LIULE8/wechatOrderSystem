package com.imooc.service.impl;

import com.imooc.converter.OrderMaster2OrderDTOConverter;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 描述: 订单主表
 *
 * @author LIULE9
 * @create 2018-10-09 6:52 PM
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  private final ProductService productService;

  private final PayService payService;

  private final OrderDetailRepository orderDetailRepository;

  private final OrderMasterRepository orderMasterRepository;

  @Autowired
  public OrderServiceImpl(ProductService productService, OrderDetailRepository orderDetailRepository, OrderMasterRepository orderMasterRepository, PayService payService) {
    this.productService = productService;
    this.orderDetailRepository = orderDetailRepository;
    this.orderMasterRepository = orderMasterRepository;
    this.payService = payService;
  }

  @Override
  @Transactional
  public OrderDTO create(OrderDTO orderDTO) {
    String orderId = KeyUtil.genUniqueKey();
    BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

    //1. 查询商品（数量，价格）
    List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
    for (OrderDetail orderDetail : orderDetailList) {
      ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
      if (productInfo == null) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      //2. 计算总价
      orderAmount = orderDetail.getProductPrice()
          .multiply(new BigDecimal(orderDetail.getProductQuantity()))
          .add(orderAmount);

      //订单详情入库
      orderDetail.setDetailId(KeyUtil.genUniqueKey());
      orderDetail.setOrderId(orderId);
      BeanUtils.copyProperties(productInfo, orderDetail);
      orderDetailRepository.save(orderDetail);
    }

    //3. 写入订单数据库（orderMaster和orderDetail）
    OrderMaster orderMaster = new OrderMaster();
    orderDTO.setOrderId(orderId);
    BeanUtils.copyProperties(orderDTO, orderMaster);
    orderMaster.setOrderAmount(orderAmount);
    orderMaster.setOrderStatus(OrderStatusEnum.New.getCode());
    orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
    orderMasterRepository.save(orderMaster);

    //4. 扣库存
    List<CartDTO> cartDTOList = orderDetailList.stream().map(orderDetail ->
        new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity())).collect(Collectors.toList());
    productService.decreaseStock(cartDTOList);

    return orderDTO;
  }

  @Override
  public OrderDTO findOne(String orderId) {
    Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
    if (!orderMasterOptional.isPresent()) {
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }

    List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
    if (CollectionUtils.isEmpty(orderDetails)) {
      throw new SellException(ResultEnum.ORDEDETAIL_NOT_EXIST);
    }

    OrderDTO orderDTO = new OrderDTO();
    BeanUtils.copyProperties(orderMasterOptional.get(), orderDTO);
    orderDTO.setOrderDetailList(orderDetails);

    return orderDTO;
  }

  @Override
  public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
    Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenId, pageable);
    List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
    return new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
  }

  @Override
  @Transactional(rollbackOn = SellException.class)
  public void cancel(OrderDTO orderDTO) {

    //判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())) {
      log.error("【取消订单】 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }

    //修改订单状态
    orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null) {
      log.error("【取消订单】 更新失败, orderMaster={}", orderMaster);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }

    //返还库存
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
      log.error("【取消订单】 订单中无商品详情, orderDTO={}", orderDTO);
      throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
    }

    List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
        .map(orderDetail -> new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity()))
        .collect(Collectors.toList());
    productService.increaseStock(cartDTOList);

    //如果已支付, 需要退款
    if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
      payService.refund(orderDTO);
    }
  }

  @Override
  @Transactional
  public OrderDTO finish(OrderDTO orderDTO) {

    //判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())) {
      log.error("【完结订单】 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }

    orderDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);

    //修改订单状态
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null) {
      log.error("【完结订单】 更新失败, orderMaster={}", orderMaster);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }

    return orderDTO;
  }

  @Override
  @Transactional(rollbackOn = SellException.class)
  public void paid(OrderDTO orderDTO) {

    //判断订单状态
    if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.New.getCode())) {
      log.error("【订单支付】 订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
      throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
    }

    //判断支付状态
    if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
      log.error("【订单支付】 支付状态不正确, orderDTO={}", orderDTO);
      throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
    }

    orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
    OrderMaster orderMaster = new OrderMaster();
    BeanUtils.copyProperties(orderDTO, orderMaster);

    //修改支付状态
    OrderMaster updateResult = orderMasterRepository.save(orderMaster);
    if (updateResult == null) {
      log.error("【订单支付】 更新失败, orderMaster={}", orderMaster);
      throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
    }
  }

}