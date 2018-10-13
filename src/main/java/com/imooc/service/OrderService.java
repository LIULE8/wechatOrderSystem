package com.imooc.service;

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
   * @param orderDTO
   * @return
   */
  OrderDTO create(OrderDTO orderDTO);

  /**
   * 查询单个订单
   * @param orderId
   * @return
   */
  OrderDTO findOne(String orderId);

  /**
   * 查询订单列表
   * @param buyerOpenId
   * @param pageable
   * @return
   */
  Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

  /**
   * 取消订单
   * @param orderDTO
   */
  void cancel(OrderDTO orderDTO);

  /**
   * 完结订单
   * @param orderDTO
   * @return
   */
  OrderDTO finish(OrderDTO orderDTO);

  /**
   * 支付订单
   * @param orderDTO
   */
  void paid(OrderDTO orderDTO);


  /**
   * 查询所有订单列表
   * @param pageable
   * @return
   */
  Page<OrderDTO> findList(Pageable pageable);

}