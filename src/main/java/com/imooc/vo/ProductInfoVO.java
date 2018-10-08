package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述: 商品详情
 *
 * @author LIULE9
 * @create 2018-10-08 7:34 PM
 */
@Data
public class ProductInfoVO {

  @JsonProperty("id")
  private String productId;

  @JsonProperty("name")
  private String productName;

  @JsonProperty("price")
  private BigDecimal productPrice;

  @JsonProperty("description")
  private String productDescription;

  @JsonProperty("Icon")
  private String productIcon;

}