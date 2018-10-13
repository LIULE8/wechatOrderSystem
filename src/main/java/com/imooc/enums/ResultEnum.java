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
   * 成功
   */
  SUCCESS(0, "成功"),

  /**
   * 判断表单参数
   */
  PARAM_ERROR(1, "参数不正确"),

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
  ORDER_PAY_STATUS_ERROR(17, "支付状态错误"),

  /**
   * 购物车不能为空
   */
  CART_EMPTY(18, "购物车不能为空"),

  /**
   * 该订单不属于当前用户
   */
  ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

  /**
   * 微信公众账号方面错误
   */
  WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

  /**
   * 微信支付异步通知金额校验不通过
   */
  WECHAT_NOTIFY_MONEY_VERIFY_ERROR(21, "微信支付异步通知金额校验不通过"),

  /**
   * 订单取消成功
   */
  ORDER_CANCEL_SUCCESS(22,"订单取消成功"),

  /**
   * 订单完结成功
   */
  ORDER_FINISH_SUCCESS(22,"订单完结成功"),

  /**
   * 商品状态不争取
   */
  PRODUCT_STATUS_ERROR(23,"商品状态不正确")
  ;

  private Integer code;

  private String message;
}
