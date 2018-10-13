package com.imooc.utils;

import com.imooc.enums.CodeEnum;

/**
 * 描述: 枚举工具类
 *
 * @author LIULE9
 * @create 2018-10-13 9:50 AM
 */
public class EnumUtil {

  public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
    for (T each : enumClass.getEnumConstants()) {
      if (code.equals(each.getCode())) {
        return each;
      }
    }
    return null;
  }
}