package com.imooc.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 描述: json格式化工具
 *
 * @author LIULE9
 * @create 2018-10-11 3:30 PM
 */
public class JsonUtil {

  public static String toJson(Object object){
    GsonBuilder gsonBuilder = new GsonBuilder();
    Gson gson = gsonBuilder.create();
    return gson.toJson(object);
  }

}