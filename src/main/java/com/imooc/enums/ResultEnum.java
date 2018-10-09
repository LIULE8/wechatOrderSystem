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
  PRODUCT_STOCK_ERROR(20, "库存不足");

  private Integer code;

  private String message;
}
