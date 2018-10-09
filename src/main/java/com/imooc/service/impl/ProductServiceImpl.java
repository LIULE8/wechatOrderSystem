package com.imooc.service.impl;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import com.imooc.enums.ProductStatusEnum;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.repository.ProductRepository;
import com.imooc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.RollbackException;
import javax.transaction.Transactional;
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
    return productInfoOptional.orElse(null);
  }

  @Override
  public List<ProductInfo> findUpAll() {
    return productRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
  }

  @Override
  public Page<ProductInfo> findAll(Pageable pageable) {
    return productRepository.findAll(pageable);
  }

  @Override
  public ProductInfo save(ProductInfo productInfo) {
    return productRepository.save(productInfo);
  }

  @Override
  public void increaseStock(List<CartDTO> cartDTOList) {

  }

  @Override
  @Transactional(rollbackOn = SellException.class)
  public void decreaseStock(List<CartDTO> cartDTOList) {
    for (CartDTO cartDTO : cartDTOList) {
      Optional<ProductInfo> productInfoOptional = productRepository.findById(cartDTO.getProductId());
      if (!productInfoOptional.isPresent()) {
        throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
      }
      ProductInfo productInfo = productInfoOptional.get();
      Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
      if (result < 0) {
        throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
      }
      productInfo.setProductStock(result);

      productRepository.save(productInfo);
    }
  }
}