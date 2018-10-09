package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 订单状态
 *
 * @author LIULE9
 * @create 2018-10-09 9:21 AM
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

  /**
   * 新订单
   */
  New(0, "新订单"),
  /**
   * 完结
   */
  FINISHED(1, "完结"),
  /**
   * 已取消
   */
  CANCEL(2, "已取消")
  ;

  private Integer code;
  private String message;

}