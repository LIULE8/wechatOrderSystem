package com.imooc.repository;

import com.imooc.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述: 商品
 *
 * @author LIULE9
 * @create 2018-10-08 2:44 PM
 */
public interface ProductRepository extends JpaRepository<ProductInfo,String> {

   List<ProductInfo> findByProductStatus(Integer productStatus);
}