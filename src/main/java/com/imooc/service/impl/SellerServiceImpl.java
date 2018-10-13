package com.imooc.service.impl;

import com.imooc.dataobject.SellerInfo;
import com.imooc.repository.SellerInfoRepository;
import com.imooc.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述: 卖家信息
 *
 * @author LIULE9
 * @create 2018-10-13 3:22 PM
 */
@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

  private final SellerInfoRepository sellerInfoRepository;

  @Autowired
  public SellerServiceImpl(SellerInfoRepository sellerInfoRepository) {
    this.sellerInfoRepository = sellerInfoRepository;
  }

  @Override
  public SellerInfo findSellerInfoByOpenid(String openid) {
      return sellerInfoRepository.findByOpenid(openid);
  }
}