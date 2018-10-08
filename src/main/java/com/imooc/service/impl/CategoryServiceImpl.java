package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import com.imooc.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author LIULE9
 */
@Service
public class CategoryServiceImpl implements CategoryService {

  private final ProductCategoryRepository productCategoryRepository;

  @Autowired
  public CategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
    this.productCategoryRepository = productCategoryRepository;
  }

  @Override
  public ProductCategory findOne(Integer categoryId) {
    Optional<ProductCategory> productCategoryOptional = productCategoryRepository.findById(categoryId);
    if (productCategoryOptional.isPresent()) {
      return productCategoryOptional.get();
    }
    throw new NullPointerException("this categoryId can not find any category");
  }

  @Override
  public List<ProductCategory> findAll() {
    return productCategoryRepository.findAll();
  }

  @Override
  public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
    return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
  }

  @Override
  public ProductCategory save(ProductCategory productCategory) {
    return productCategoryRepository.save(productCategory);
  }
}
