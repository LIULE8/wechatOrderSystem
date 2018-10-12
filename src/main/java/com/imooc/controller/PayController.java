package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import com.imooc.service.PayService;
import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述: 支付
 *
 * @author LIULE9
 * @create 2018-10-11 3:06 PM
 */
@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

  private final OrderService orderService;

  private final PayService payService;

  @Autowired
  public PayController(OrderService orderService, PayService payService) {
    this.orderService = orderService;
    this.payService = payService;
  }

  @GetMapping("/create")
  public ModelAndView create(@RequestParam("orderId") String orderId,
                             @RequestParam("returnUrl") String returnUrl) {
    //1. 查询订单
    OrderDTO orderDTO = orderService.findOne(orderId);
    if (orderDTO == null) {
      throw new SellException(ResultEnum.ORDER_NOT_EXIST);
    }

    //2. 发起支付
    PayResponse payResponse = payService.create(orderDTO);
    ModelAndView modelAndView = new ModelAndView("pay/create");
    modelAndView.addObject("payResponse", payResponse);
    modelAndView.addObject("returnUrl", returnUrl);
    return modelAndView;
  }

  /**
   * 微信异步通知
   *
   * @param notifyData
   */
  @PostMapping("/notify")
  public ModelAndView notify(@RequestBody String notifyData) {
    payService.notify(notifyData);

    //返回给微信处理结果
    return new ModelAndView("pay/success");
  }

}