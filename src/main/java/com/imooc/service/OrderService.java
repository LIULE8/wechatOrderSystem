package com.imooc.service;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 描述: 订单主表
 *
 * @author LIULE9
 * @create 2018-10-09 10:11 AM
 */
public interface OrderService {

  /**
   * 创建订单
   */
  OrderDTO create(OrderDTO orderDTO);

  /**
   * 查询单个订单
   */
  OrderDTO findOne(String orderId);

  /**
   * 查询订单列表
   */
  Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

  /**
   * 取消订单
   */
  OrderDTO cancel(OrderDTO orderDTO);

  /**
   * 完结订单
   */
  OrderDTO finish(OrderDTO orderDTO);

  /**
   * 支付订单
   */
  OrderDTO paid(OrderDTO orderDTO);
}