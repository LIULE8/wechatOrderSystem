package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.repository.ProductRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 描述: 商品
 *
 * @author LIULE9
 * @create 2018-10-08 3:53 PM
 */
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public ProductInfo findOne(String productId) {
    Optional<ProductInfo> productInfoOptional = productRepository.findById(productId);
    if(productInfoOptional.isPresent()){
      return productInfoOptional.get();
    }
    throw new NullPointerException("can not find any product by this productId");
  }

  @Override
  public List<ProductInfo> findUpAll(Integer productStatus) {
    return productRepository.findByProductStatus(0);
  }

  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return productRepository.save(productInfo);
  }
}