package com.imooc.service.impl;

import com.imooc.dataobject.ProductCategory;
import com.imooc.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {

  @InjectMocks
  private CategoryServiceImpl categoryService;

  @Mock
  private ProductCategoryRepository productCategoryRepository;

  @Test
  public void should_return_a_product_category_when_invoke_findOne_method_and_input_1() {
    when(productCategoryRepository.findById(1)).thenReturn(java.util.Optional.of(new ProductCategory()));

    ProductCategory productCategory = categoryService.findOne(1);

    Assert.assertNotNull(productCategory);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_null_point_exception_when_invoke_findOne_method_and_input_1() {
    categoryService.findOne(1);
  }

  @Test
  public void should_return_all_category_when_find_all_category() {
    when(productCategoryRepository.findAll()).thenReturn(new ArrayList<ProductCategory>() {{
      add(new ProductCategory());
      add(new ProductCategory());
    }});
    List<ProductCategory> productCategoryList = categoryService.findAll();
    Assert.assertEquals(2, productCategoryList.size());
  }

  @Test
  public void should_return_relevant_category_when_find_category_by_category_id_list() {
    ArrayList<Integer> categoryTypeList = new ArrayList<Integer>() {{
      add(1);
      add(2);
      add(3);
      add(4);
    }};
    when(productCategoryRepository.findByCategoryTypeIn(categoryTypeList)).thenReturn(new ArrayList<ProductCategory>() {{
      add(new ProductCategory());
      add(new ProductCategory());
    }});
    List<ProductCategory> productCategories = categoryService.findByCategoryTypeIn(categoryTypeList);
    Assert.assertEquals(2, productCategories.size());
  }

  @Test
  public void should_return_a_category_with_category_id_when_save_a_new_category() {
    ProductCategory productCategory = new ProductCategory();
    ProductCategory savedProductCategory = new ProductCategory();
    when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);
    savedProductCategory.setCategoryId(1);
    savedProductCategory = categoryService.save(productCategory);
    Assert.assertEquals(Integer.valueOf(1), savedProductCategory.getCategoryId());
  }
}