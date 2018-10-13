package com.imooc.form;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 描述: 类目表单
 *
 * @author LIULE9
 * @create 2018-10-13 2:40 PM
 */
@Data
public class CategoryFrom {

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