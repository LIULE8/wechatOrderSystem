package com.imooc.service;

import com.imooc.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;

/**
 * 描述: 支付
 *
 * @author LIULE9
 * @create 2018-10-11 3:08 PM
 */
public interface PayService {

  PayResponse create(OrderDTO orderDTO);

  void notify(String notifyData);

  void refund(OrderDTO orderDTO);
}