package com.imooc.form;

import com.imooc.dataobject.OrderDetail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 描述: 表单验证
 *
 * @author LIULE9
 * @create 2018-10-10 8:34 PM
 */
@Data
public class OrderForm {

  /**
   * 买家姓名
   */
  @NotEmpty(message = "姓名必填")
  private String name;

  /**
   * 买家手机号
   */
  @NotEmpty(message = "手机号必填")
  private String phone;

  /**
   * 买家地址
   */
  @NotEmpty(message = "地址必填")
  private String address;

  /**
   * 买家微信openid
   */
  @NotEmpty(message = "openid必填")
  private String openid;

  /**
   * 购物车信息
   */
  @NotEmpty(message = "购物车不能为空")
  private String items;

}