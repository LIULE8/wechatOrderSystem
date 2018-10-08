package com.imooc.utils;

import com.imooc.vo.ResultVO;

import java.util.List;

/**
 * 描述: http返回对象的工具类
 *
 * @author LIULE9
 * @create 2018-10-08 8:13 PM
 */
public class ResultVOUtil {

  public static ResultVO success(List<?> object){
    ResultVO resultVO = new ResultVO<>();
    resultVO.setData(object);
    resultVO.setCode(0);
    resultVO.setMsg("成功");
    return resultVO;
  }

  public static ResultVO success(){
    return success(null);
  }

  public static ResultVO error(Integer code, String msg){
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }
}