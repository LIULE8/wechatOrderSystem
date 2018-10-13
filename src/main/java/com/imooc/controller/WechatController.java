package com.imooc.controller;

import com.imooc.config.ProjectUrlConfig;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * 描述: 微信第三方sdk
 *
 * @author LIULE9
 * @create 2018-10-11 1:43 PM
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

  private final WxMpService wxMpService;

  private final WxMpService wxOpenService;

  private final ProjectUrlConfig projectUrlConfig;

  @Autowired
  public WechatController(WxMpService wxMpService, WxMpService wxOpenService, ProjectUrlConfig projectUrlConfig) {
    this.wxMpService = wxMpService;
    this.wxOpenService = wxOpenService;
    this.projectUrlConfig = projectUrlConfig;
  }


  @GetMapping("/authorize")
  public String authorize(@RequestParam("returnUrl") String returnUrl) {
    //1.配置
    //2.调用方法
    String url = projectUrlConfig.wechatMpAuthorize + "/sell/wechat/userInfo";
    String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url,
        WxConsts.OAuth2Scope.SNSAPI_USERINFO,
        URLEncoder.encode(returnUrl, StandardCharsets.UTF_8));
    log.info("【微信网页授权】 获取code, redirectUrl={}", redirectUrl);
    return "redirect:" + redirectUrl;
  }

  @GetMapping("/userInfo")
  public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl) {
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
    try {
      wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
      log.error("【微信网页授权】 {}", e);
      throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
    }

    String openId = wxMpOAuth2AccessToken.getOpenId();
    return "redirect:" + returnUrl + "?openid=" + openId;
  }


  @GetMapping("/qrAuthorize")
  public String qrAuthorize(@RequestParam("returnUrl")String returnUrl){
    String url = projectUrlConfig.wechatOpenAuthorize + "/sell/wechat/qrUserInfo";
    String redirectUrl = wxOpenService.buildQrConnectUrl(url,
        WxConsts.QrConnectScope.SNSAPI_LOGIN,
        URLEncoder.encode(returnUrl, StandardCharsets.UTF_8));
    return "redirect:" + redirectUrl;
  }

  @GetMapping("/qrUserInfo")
  public String qrUserInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl){
    WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
    try {
      wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
    } catch (WxErrorException e) {
      log.error("【微信网页授权】 {}", e);
      throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
    }

    String openId = wxMpOAuth2AccessToken.getOpenId();
    return "redirect:" + returnUrl + "?openid=" + openId;

  }


}