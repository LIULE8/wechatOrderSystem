package com.imooc.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 描述: 商品
 *
 * @author LIULE9
 * @create 2018-10-08 2:34 PM
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
public class ProductInfo {

  @Id
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
  private Integer productStatus;

  /**
   * 类目编号
   */
  private Integer categoryType;
}