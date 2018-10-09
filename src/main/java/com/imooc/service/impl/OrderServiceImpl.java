package com.imooc.service.impl;

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
import com.imooc.service.ProductService;
import com.imooc.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: 订单主表
 *
 * @author LIULE9
 * @create 2018-10-09 6:52 PM
 */
@Service
public class OrderServiceImpl implements OrderService {

  private final ProductService productService;

  private final OrderDetailRepository orderDetailRepository;

  private final OrderMasterRepository orderMasterRepository;

  @Autowired
  public OrderServiceImpl(ProductService productService, OrderDetailRepository orderDetailRepository, OrderMasterRepository orderMasterRepository) {
    this.productService = productService;
    this.orderDetailRepository = orderDetailRepository;
    this.orderMasterRepository = orderMasterRepository;
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
    BeanUtils.copyProperties(orderDTO,orderMaster);
    orderMaster.setOrderId(orderId);
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
    return null;
  }

  @Override
  public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
    return null;
  }

  @Override
  public OrderDTO cancel(OrderDTO orderDTO) {
    return null;
  }

  @Override
  public OrderDTO finish(OrderDTO orderDTO) {
    return null;
  }

  @Override
  public OrderDTO paid(OrderDTO orderDTO) {
    return null;
  }
}