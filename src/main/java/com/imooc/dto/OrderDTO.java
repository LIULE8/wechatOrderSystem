package com.imooc.dto;

import com.imooc.dataobject.OrderDetail;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 描述: 订单传输对象
 *
 * @author LIULE9
 * @create 2018-10-09 10:18 AM
 */
@Data
public class OrderDTO {

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
  private Integer orderStatus;

  /**
   * 支付状态,默认为0，未支付
   */
  private Integer payStatus;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 更新时间
   */
  private Date updateTime;

  /**
   * 关联的订单详情，分页查询出来的
   */
  List<OrderDetail> orderDetailList;
}