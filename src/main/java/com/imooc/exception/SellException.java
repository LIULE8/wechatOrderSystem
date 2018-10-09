package com.imooc.exception;

import com.imooc.enums.ResultEnum;

/**
 * 描述: 自定义异常
 *
 * @author LIULE9
 * @create 2018-10-09 7:38 PM
 */
public class SellException extends RuntimeException {

  private Integer code;

  public SellException(ResultEnum resultEnum) {
    super(resultEnum.getMessage());
    this.code = resultEnum.getCode();
  }
}