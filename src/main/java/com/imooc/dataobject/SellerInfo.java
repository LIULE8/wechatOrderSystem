package com.imooc.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 描述: 卖家信息
 *
 * @author LIULE9
 * @create 2018-10-13 3:18 PM
 */
@Data
@Entity
public class SellerInfo {

  @Id
  private String sellerId;

  private String username;

  private String password;

  private String openid;

}