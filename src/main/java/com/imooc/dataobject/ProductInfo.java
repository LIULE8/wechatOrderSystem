package com.imooc.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.utils.EnumUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@DynamicUpdate
public class ProductInfo implements Serializable {

  private static final long serialVersionUID = 6399186181668983148L;

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

  private Date createTime;

  private Date updateTime;

  @JsonIgnore
  public ProductStatusEnum getProductStatusEnum() {
    return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
  }
}