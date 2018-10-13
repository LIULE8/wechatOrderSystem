package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 商品状态
 *
 * @author LIULE9
 * @create 2018-10-08 2:44 PM
 */
@Getter
@AllArgsConstructor
public enum ProductStatusEnum implements CodeEnum{

  /**
   * 商品在架
   */
  UP(0, "在架"),

  /**
   * 商品下架
   */
  DOWN(1, "下架")
  ;

  private Integer code;

  private String message;

}
