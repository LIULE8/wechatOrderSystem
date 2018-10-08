package com.imooc.repository;

import com.imooc.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductCategoryRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ProductCategoryRepository productCategoryRepository;

  @Test
  public void should_return_a_product_category_when_call_findById_and_input_1() {
    ProductCategory productCategory = new ProductCategory();
    productCategory.setCategoryName("书本");
    productCategory.setCategoryType(200);
    entityManager.persistAndFlush(productCategory);
    Optional<ProductCategory> optional = productCategoryRepository.findById(1);
    optional.ifPresent(productCategory1 -> Assert.assertEquals(1, productCategory1.getCategoryId().intValue()));
  }
}