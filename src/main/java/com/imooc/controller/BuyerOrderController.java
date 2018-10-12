package com.imooc.controller;

import com.imooc.converter.OrderForm2OrderDTOConverter;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import com.imooc.service.BuyerService;
import com.imooc.service.OrderService;
import com.imooc.utils.ResultVOUtil;
import com.imooc.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 描述: 订单
 *
 * @author LIULE9
 * @create 2018-10-10 8:31 PM
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

  private final OrderService orderService;

  private final BuyerService buyerService;

  @Autowired
  public BuyerOrderController(OrderService orderService, BuyerService buyerService) {
    this.orderService = orderService;
    this.buyerService = buyerService;
  }

  /**
   * 创建订单
   *
   * @param orderForm
   * @param bindingResult
   * @return
   */
  @SuppressWarnings("all")
  @PostMapping("/create")
  public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      log.error("【创建订单】参数不正确, orderForm={}", orderForm);
      throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
          Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    }

    OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
    if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
      log.error("【创建订单】购物车不能为空");
      throw new SellException(ResultEnum.CART_EMPTY);
    }

    OrderDTO createResult = orderService.create(orderDTO);
    Map<String, String> map = new HashMap<>();
    map.put("orderId", createResult.getOrderId());

    return ResultVOUtil.success(map);

  }

  /**
   * 订单列表
   *
   * @param openid
   * @param page
   * @param size
   * @return
   */
  @SuppressWarnings("all")
  @GetMapping("/list")
  public ResultVO<List<OrderDTO>> getList(@RequestParam("openid") String openid,
                                          @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {

    if (StringUtils.isEmpty(openid)) {
      log.error("【查询订单列表】openid为空");
      throw new SellException(ResultEnum.PARAM_ERROR);
    }
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);
    return ResultVOUtil.success(orderDTOPage.getContent());

  }

  /**
   * 单个订单详情
   *
   * @param openid
   * @param orderId
   * @return
   */
  @SuppressWarnings("all")
  @GetMapping("/detail")
  public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                   @RequestParam("orderId") String orderId) {
    if (StringUtils.isEmpty(openid)) {
      log.error("【查询订单详情】openid为空");
      throw new SellException(ResultEnum.PARAM_ERROR);
    }

    if (StringUtils.isEmpty(openid)) {
      log.error("【查询订单详情】orderId为空");
      throw new SellException(ResultEnum.PARAM_ERROR);
    }

    OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);

    return ResultVOUtil.success(orderDTO);
  }


  /**
   * 取消订单
   *
   * @param openid
   * @param orderId
   * @return
   */
  @PostMapping("/cancel")
  public ResultVO cancel(@RequestParam("openid") String openid,
                         @RequestParam("orderId") String orderId) {

    if (StringUtils.isEmpty(openid)) {
      log.error("【查询订单详情】openid为空");
      throw new SellException(ResultEnum.PARAM_ERROR);
    }

    if (StringUtils.isEmpty(openid)) {
      log.error("【查询订单详情】orderId为空");
      throw new SellException(ResultEnum.PARAM_ERROR);
    }

    buyerService.cancelOrder(openid, orderId);

    return ResultVOUtil.success();
  }
}