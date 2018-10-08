package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void should_return_product_list_with_status_equals_0_when_find_normal_products() {
    ProductInfo productInfo1 = new ProductInfo();
    productInfo1.setProductId("1");
    productInfo1.setProductName("normal");
    productInfo1.setProductPrice(new BigDecimal(100));
    productInfo1.setProductStock(10);
    productInfo1.setProductStatus(ProductStatusEnum.UP.getCode());
    productInfo1.setCategoryType(1);
    ProductInfo productInfo2 = new ProductInfo();
    productInfo2.setProductId("2");
    productInfo2.setProductName("downShelf");
    productInfo2.setProductPrice(new BigDecimal(200));
    productInfo2.setProductStock(20);
    productInfo2.setProductStatus(ProductStatusEnum.DOWN.getCode());
    productInfo2.setCategoryType(2);
    entityManager.persistAndFlush(productInfo1);
    entityManager.persistAndFlush(productInfo2);

    List<ProductInfo> productInfoLists = productRepository.findByProductStatus(0);

    Assert.assertEquals("1",productInfoLists.get(0).getProductId());

  }
}