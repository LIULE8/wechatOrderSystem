package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 商品查找结果状态
 *
 * @author LIULE9
 * @create 2018-10-09 7:39 PM
 */
@Getter
@AllArgsConstructor
public enum ResultEnum {

  /**
   * 商品不存在
   */
  PRODUCT_NOT_EXIST(10, "商品不存在"),

  /**
   * 库存不足
   */
  PRODUCT_STOCK_ERROR(11, "库存不正确"),

  /**
   * 订单不存在
   */
  ORDER_NOT_EXIST(12, "订单不存在"),

  /**
   * 订单详情不存在
   */
  ORDEDETAIL_NOT_EXIST(13, "订单详情不存在"),

  /**
   * 订单状态错误
   */
  ORDER_STATUS_ERROR(14, "订单状态错误"),

  /**
   * 订单更新失败
   */
  ORDER_UPDATE_FAIL(15, "订单更新失败"),

  /**
   * 订单详情为空
   */
  ORDER_DETAIL_EMPTY(16, "订单详情为空"),

  /**
   * 支付状态错误
   */
  ORDER_PAY_STATUS_ERROR(17, "支付状态错误");

  private Integer code;

  private String message;
}
