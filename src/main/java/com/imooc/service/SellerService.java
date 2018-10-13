package com.imooc.service;

import com.imooc.dataobject.SellerInfo;

/**
 * 描述: 卖家信息
 *
 * @author LIULE9
 * @create 2018-10-13 3:21 PM
 */
public interface SellerService {

  /**
   * 查询卖家信息
   * @param openid
   * @return
   */
  SellerInfo findSellerInfoByOpenid(String openid);
}