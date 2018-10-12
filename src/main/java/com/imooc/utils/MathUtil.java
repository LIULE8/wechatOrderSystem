package com.imooc.utils;

/**
 * 描述: 金额比较
 *
 * @author LIULE9
 * @create 2018-10-12 3:05 PM
 */
public class MathUtil {

  private static final Double MONEY_RANGE = 0.01;

  /**
   * 比较2个金额是否相等
   *
   * @param d1
   * @param d2
   * @return
   */
  public static boolean equals(Double d1, Double d2) {
    double abs = Math.abs(d1 - d2);
    return abs < MONEY_RANGE;
  }

}