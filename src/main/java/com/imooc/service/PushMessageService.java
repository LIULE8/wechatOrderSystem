package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * 描述: 推送消息
 *
 * @author LIULE9
 * @create 2018-10-15 6:46 PM
 */
public interface PushMessageService {

  /**
   * 订单状态变更消息
   * @param orderDTO
   */
  void orderStatus(OrderDTO orderDTO);
}