package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述: 商品视图(商品，类目)
 *
 * @author LIULE9
 * @create 2018-10-08 7:30 PM
 */
@Data
public class ProductVO {

  @JsonProperty("name")
  private String categoryName;

  @JsonProperty("type")
  private Integer categoryType;

  @JsonProperty("foods")
  private List<ProductInfoVO> productInfoVOList;

}