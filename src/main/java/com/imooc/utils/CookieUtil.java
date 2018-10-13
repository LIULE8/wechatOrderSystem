package com.imooc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述: cookie工具类
 *
 * @author LIULE9
 * @create 2018-10-13 4:36 PM
 */
public class CookieUtil {

  /**
   * 设置cookie
   * @param response
   * @param name
   * @param value
   * @param maxAge
   */
  public static void setCookie(HttpServletResponse response,
                               String name,
                               String value,
                               int maxAge){
    Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    response.addCookie(cookie);
  }

  public static Cookie getCookie(){
    return null;
  }
}