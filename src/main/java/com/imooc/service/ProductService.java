package com.imooc.service;

import com.imooc.dataobject.ProductInfo;
import com.imooc.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 描述: 商品
 *
 * @author LIULE9
 * @create 2018-10-08 3:34 PM
 */
public interface ProductService {

  ProductInfo findOne(String productId);

  /**
   * 查询所有在架的商品
   * @return
   */
  List<ProductInfo> findUpAll();

  Page<ProductInfo> findAll(Pageable pageable);

  ProductInfo save(ProductInfo productInfo);

  /**
   * 加库存
   */
  void increaseStock(List<CartDTO> cartDTOList);

  /**
   * 减库存
   */
  void decreaseStock(List<CartDTO> cartDTOList);

  /**
   * 下架
   * @param productId
   * @return
   */
  ProductInfo offSale(String productId);

  /**
   * 上架
   * @param productId
   * @return
   */
  ProductInfo onSale(String productId);


}