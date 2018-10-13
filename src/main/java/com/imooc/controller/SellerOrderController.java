package com.imooc.controller;

import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * 描述: 卖家端订单
 *
 * @author LIULE9
 * @create 2018-10-12 7:43 PM
 */
@Controller
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {

  private final OrderService orderService;

  @Autowired
  public SellerOrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  /**
   * 订单列表
   *
   * @param page 第几页， 从1页开始
   * @param size 一页有多少条数据
   * @return
   */
  @GetMapping("/list")
  public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
    PageRequest pageRequest = PageRequest.of(page - 1, size);
    Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
    ModelAndView modelAndView = new ModelAndView("order/list");
    modelAndView.addObject("orderDTOPage", orderDTOPage);
    modelAndView.addObject("currentPage", page);
    modelAndView.addObject("size", size);
    return modelAndView;
  }

  /**
   * 取消订单
   *
   * @param orderId
   * @return
   */
  @GetMapping("/cancel")
  public ModelAndView cancel(@RequestParam("orderId") String orderId) {
    OrderDTO orderDTO;
    try {
      orderDTO = orderService.findOne(orderId);
    } catch (SellException e) {
      log.error("【卖家端取消订单】 发生异常={}", e);
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", e.getMessage());
      error.addObject("url", "/sell/seller/order/list");
      return error;
    }
    orderService.cancel(orderDTO);
    ModelAndView success = new ModelAndView("order/success");
    success.addObject("msg", ResultEnum.ORDER_CANCEL_SUCCESS);
    success.addObject("utl", "/sell/seller/order/list");
    return success;
  }

  /**
   * 查询订单详情
   * @param orderId
   * @return
   */
  @GetMapping("/detail")
  public ModelAndView detail(@RequestParam("orderId") String orderId) {
    OrderDTO orderDTO;
    try {
      orderDTO = orderService.findOne(orderId);
    } catch (SellException e) {
      log.error("【卖家端查询订单详情】 发生异常={}", e);
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", e.getMessage());
      error.addObject("url", "/sell/seller/order/list");
      return error;
    }
    ModelAndView success = new ModelAndView("order/detail");
    success.addObject("orderDTO", orderDTO);
    return success;
  }

  /**
   * 完结订单
   * @param orderId
   * @return
   */
  @GetMapping("/finish")
  public ModelAndView finish(@RequestParam("orderId") String orderId){
    try {
      OrderDTO orderDTO = orderService.findOne(orderId);
      orderService.finish(orderDTO);
    } catch (SellException e) {
      log.error("【卖家端完结订单】 发生异常={}", e);
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", e.getMessage());
      error.addObject("url", "/sell/seller/order/list");
      return error;
    }
    ModelAndView success = new ModelAndView("order/success");
    success.addObject("msg", ResultEnum.ORDER_FINISH_SUCCESS);
    success.addObject("utl", "/sell/seller/order/list");
    return success;
  }

}