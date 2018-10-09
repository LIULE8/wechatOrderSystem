package com.imooc.dataobject;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 描述: 订单主表
 *
 * @author LIULE9
 * @create 2018-10-09 9:10 AM
 */
@Entity
@Data
@DynamicUpdate //允许db自动更新的注解，可以用来允许db自动更新update时间
public class OrderMaster {

  /**
   * 订单id
   */
  @Id
  private String orderId;

  /**
   * 买家名字
   */
  private String buyerName;

  /**
   * 买家手机号
   */
  private String buyerPhone;

  /**
   * 买家地址
   */
  private String buyerAddress;

  /**
   * 买家微信OpenId
   */
  private String buyerOpenid;

  /**
   * 订单总金额
   */
  private BigDecimal orderAmount;

  /**
   * 订单状态，默认为0，新下单.
   */
  private Integer orderStatus = OrderStatusEnum.New.getCode();

  /**
   * 支付状态,默认为0，未支付
   */
  private Integer payStatus = PayStatusEnum.WAIT.getCode();

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;

}