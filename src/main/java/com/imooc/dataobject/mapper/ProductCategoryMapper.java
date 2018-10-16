package com.imooc.dataobject.mapper;

import com.imooc.dataobject.ProductCategory;
import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * 描述: 类目的mapper
 *
 * @author LIULE9
 * @create 2018-10-16 2:16 PM
 */
public interface ProductCategoryMapper {

  @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER)")
  int insertByMap(Map<String,Object> map);


  ProductCategory selectByCategoryType(Integer categoryType);
}