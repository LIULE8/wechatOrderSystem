package com.imooc.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目
 *
 * @author LIULE9
 */
@Entity
@Data
@ToString
@EqualsAndHashCode
@DynamicUpdate
public class ProductCategory {

  /**
   * 类目id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer categoryId;
  /**
   * 类目名字.
   */
  private String categoryName;
  /**
   * 类目编号.
   */
  private Integer categoryType;

  private Date createTime;

  private Date updateTime;

}
