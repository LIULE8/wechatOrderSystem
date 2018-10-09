package com.imooc.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述: 支付状态
 *
 * @author LIULE9
 * @create 2018-10-09 9:25 AM
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum {

  /**
   * 未支付
   */
  WAIT(0, "等待支付"),

  /**
   * 支付成功
   */
  SUCCESS(1, "支付成功"),
  ;

  private Integer code;
  private String message;
}
