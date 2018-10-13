package com.imooc.aspect;

import com.imooc.constant.CookieConstant;
import com.imooc.constant.RedisConstant;
import com.imooc.exception.SellException;
import com.imooc.exception.SellerAuthorizeException;
import com.imooc.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 描述: 卖家登录认证校验
 *
 * @author LIULE9
 * @create 2018-10-13 5:17 PM
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

  private final StringRedisTemplate redisTemplate;

  @Autowired
  public SellerAuthorizeAspect(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  @Pointcut("execution(public * com.imooc.controller.Seller*.*(..))" +
      "&& !execution(public * com.imooc.controller.SellerUserController.*(..))")
  public void verify() {
  }

  @Before("verify()")
  public void doVerify() {
    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

    HttpServletRequest request = attributes.getRequest();
    //查询cookie
    Cookie cookie = CookieUtil.getCookie(request, CookieConstant.TOKEN);
    if (cookie == null) {
      log.warn("【登录校验】 Cookie中查不到token");
      throw new SellerAuthorizeException();
    }

    //去redis里查询
    String token = RedisConstant.TOKEN_PREFIX + cookie.getValue();
    String tokenValue = redisTemplate.opsForValue().get(token);
    if (StringUtils.isEmpty(tokenValue)){
      log.warn("【登录校验】 Redis中查不到token");
      throw new SellerAuthorizeException();
    }
  }
}