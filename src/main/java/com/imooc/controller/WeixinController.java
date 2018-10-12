package com.imooc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 描述: 接收微信信息
 *
 * @author LIULE9
 * @create 2018-10-11 1:21 PM
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

  @GetMapping("/auth")
  public void auth(@RequestParam("code") String code) {
    log.info("进入auth方法。。。");
    log.info("code={}", code);

    //TODO 需要微信公众平台上找access_token的链接
    String url = "" + code;
    RestTemplate restTemplate = new RestTemplate();
    String response = restTemplate.getForObject(url, String.class);
    log.info("response={}", response);
    //resposne里面包含了openid
  }
}