package com.imooc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 描述: 购物车
 *
 * @author LIULE9
 * @create 2018-10-09 8:03 PM
 */
@Data
@AllArgsConstructor
public class CartDTO {

  /**
   * 商品Id
   */
  private String productId;

  /**
   * 商品数量
   */
  private Integer productQuantity;

}