package com.imooc.service;

import com.imooc.dto.OrderDTO;

/**
 * 描述: 买家服务
 *
 * @author LIULE9
 * @create 2018-10-11 11:10 AM
 */
public interface BuyerService {


  /**
   * 查询一个订单
   * @param openid
   * @param orderId
   * @return
   */
  OrderDTO findOrderOne(String openid, String orderId);

  /**
   * 取消订单
   *
   * @param openid
   * @param orderId
   * @return
   */
  OrderDTO cancelOrder(String openid, String orderId);


}