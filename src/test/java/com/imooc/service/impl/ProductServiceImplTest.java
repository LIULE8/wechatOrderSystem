package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

  @InjectMocks
  private ProductServiceImpl productService;

  @Mock
  private ProductRepository productRepository;

  @Test
  public void should_return_a_product_with_id_is_1_when_input_id_is_1() {
    when(productRepository.findById("1")).thenReturn(java.util.Optional.of(new ProductInfo()));

    ProductInfo productInfo = productService.findOne("1");

    Assert.assertNotNull(productInfo);
  }

  @Test
  public void should_return_all_status_is_0_products_when_find_on_sell_product() {
    when(productRepository.findByProductStatus(ProductStatusEnum.UP.getCode())).thenReturn(new ArrayList<ProductInfo>() {{
      add(new ProductInfo());
    }});

    List<ProductInfo> productInfoList = productService.findUpAll();

    Assert.assertNotEquals(0, productInfoList.size());
  }

  @Test
  public void should_return_page_product_list_when_find_all_products() {
    PageRequest pageRequest = PageRequest.of(0, 2);
    when(productRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(new ArrayList<ProductInfo>() {{
      add(new ProductInfo());
      add(new ProductInfo());
    }}, pageRequest, 2));

    Page<ProductInfo> pageProductInfo = productService.findAll(pageRequest);

    Assert.assertNotEquals(0, pageProductInfo.getContent());
  }

  @Test
  public void should_return_a_product_not_null_when_save_a_new_product() {
    ProductInfo productInfo = new ProductInfo();
    productInfo.setProductId("1");
    productInfo.setProductName("normal");
    productInfo.setProductPrice(new BigDecimal(100));
    productInfo.setProductStock(10);
    productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
    productInfo.setCategoryType(1);
    when(productRepository.save(productInfo)).thenReturn(productInfo);

    ProductInfo saveProductInfo = productService.save(productInfo);

    Assert.assertNotNull(saveProductInfo);
  }

}