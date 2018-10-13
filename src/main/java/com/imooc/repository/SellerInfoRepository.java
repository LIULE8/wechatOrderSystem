package com.imooc.repository;

import com.imooc.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述: 卖家信息
 *
 * @author LIULE9
 * @create 2018-10-13 3:19 PM
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

  SellerInfo findByOpenid(String openid);
}