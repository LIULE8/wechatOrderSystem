package com.imooc.utils;

import java.util.Random;

/**
 * 描述: 键工具类
 *
 * @author LIULE9
 * @create 2018-10-09 7:53 PM
 */
public class KeyUtil {

  /**
   * 生成唯一的主键
   * 格式：时间+随机数
   *
   * @return
   */
  public static synchronized String genUniqueKey() {
    Random random = new Random();
    Integer a = random.nextInt(900000) + 100000;
    return System.currentTimeMillis() + String.valueOf(a);
  }

}