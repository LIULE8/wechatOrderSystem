package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.dataobject.SellerInfo;
import com.imooc.enums.ResultEnum;
import com.imooc.service.SellerService;
import com.imooc.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 卖家用户
 *
 * @author LIULE9
 * @create 2018-10-13 4:04 PM
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

  private final SellerService sellerService;

  private final StringRedisTemplate redisTemplate;

  private final ProjectUrlConfig projectUrlConfig;

  @Autowired
  public SellerUserController(SellerService sellerService, StringRedisTemplate redisTemplate, ProjectUrlConfig projectUrlConfig) {
    this.sellerService = sellerService;
    this.redisTemplate = redisTemplate;
    this.projectUrlConfig = projectUrlConfig;
  }

  @GetMapping("/login")
  public ModelAndView login(@RequestParam("openid") String openid,
                            HttpServletResponse response) {

    //1. 需要openid和数据库里的数据匹配
    SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
    if (sellerInfo == null) {
      ModelAndView error = new ModelAndView("common/error");
      error.addObject("msg", ResultEnum.LOGIN_FAIL.getMessage());
      error.addObject("url", "/sell/seller/order/list");
      return error;
    }

    //2. 设置token至redis
    String token = RedisConstant.TOKEN_PREFIX + UUID.randomUUID().toString();
    Integer expire = RedisConstant.EXPIRE;

    redisTemplate.opsForValue().set(token, openid, expire, TimeUnit.SECONDS);

    //3. 设置token至cookie
    CookieUtil.setCookie(response, CookieConstant.TOKEN, openid, expire);

    //跳转用完整的http地址
    return new ModelAndView("redirect:" + projectUrlConfig.getSell() + "/sell/seller/order/list");
  }

  @GetMapping("/logout")
  public void logout() {

  }
}