package com.imooc.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 描述: http请求返回的最外层对象
 *
 * @author LIULE9
 * @create 2018-10-08 7:19 PM
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> implements Serializable {

  private static final long serialVersionUID = -8349181922768084604L;

  /**
   * 错误码
   */
  private Integer code;

  /**
   * 提示信息
   */
  private String msg;

  /**
   * 具体内容
   */
  private T data;
}