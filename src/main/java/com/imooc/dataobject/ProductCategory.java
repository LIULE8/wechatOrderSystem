package com.imooc.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 类目
 *
 * @author LIULE9
 */
@Entity
@DynamicUpdate //允许db自动更新的注解，可以用来允许db自动更新update时间
@Data
@ToString
@EqualsAndHashCode
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

}
