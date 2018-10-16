package com.imooc.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 描述: 商品视图(商品，类目)
 *
 * @author LIULE9
 * @create 2018-10-08 7:30 PM
 */
@Data
public class ProductVO implements Serializable {

  private static final long serialVersionUID = -3555231358992190972L;
  @JsonProperty("name")
  private String categoryName;

  @JsonProperty("type")
  private Integer categoryType;

  @JsonProperty("foods")
  private List<ProductInfoVO> productInfoVOList;

}