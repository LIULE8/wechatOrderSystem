package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author LIULE9
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
