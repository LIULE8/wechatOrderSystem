package com.imooc.form;

import com.imooc.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述: 商品表单
 *
 * @author LIULE9
 * @create 2018-10-13 1:36 PM
 */
@Data
public class ProductForm {

  private String productId;
  /**
   * 名字
   */
  private String productName;

  /**
   * 单价
   */
  private BigDecimal productPrice;

  /**
   * 库存
   */
  private Integer productStock;

  /**
   * 描述
   */
  private String productDescription;

  /**
   * 小图
   */
  private String productIcon;

  /**
   * 状态，0正常，1下架
   */
  private Integer productStatus = ProductStatusEnum.UP.getCode();

  /**
   * 类目编号
   */
  private Integer categoryType;

}