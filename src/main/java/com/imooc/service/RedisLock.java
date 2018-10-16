package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 描述: Redis设置分布式锁
 *
 * @author LIULE9
 * @create 2018-10-16 3:31 PM
 */
@Component
@Slf4j
public class RedisLock {

  private final StringRedisTemplate redisTemplate;

  @Autowired
  public RedisLock(StringRedisTemplate redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  /**
   * 加锁
   *
   * @param key
   * @param value 当前时间 + 超时时间
   * @return
   */
  public boolean lock(String key, String value) {
    if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
      return true;
    }

    String currentValue = redisTemplate.opsForValue().get(key);
    //如果锁过期
    if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
      //获取上一个锁的时间
      String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
      return !StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue);
    }
    return false;
  }

  /**
   * 解锁
   *
   * @param key
   * @param value
   */
  public void unlock(String key, String value) {
    try {
      String currentValue = redisTemplate.opsForValue().get(key);
      if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
        redisTemplate.opsForValue().getOperations().delete(key);
      }
    } catch (Exception e) {
      log.error("【redis分布式锁】 解锁异常, {}", e);
    }
  }

}