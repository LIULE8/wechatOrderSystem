package com.imooc.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 描述: 微信公众号配置
 *
 * @author LIULE9
 * @create 2018-10-11 1:46 PM
 */
@Component
public class WechatMpConfig {

  private final WeChatAccountConfig weChatAccountConfig;

  @Autowired
  public WechatMpConfig(WeChatAccountConfig weChatAccountConfig) {
    this.weChatAccountConfig = weChatAccountConfig;
  }

  @Bean
  public WxMpService wxMpService() {
    WxMpService wxMpService = new WxMpServiceImpl();
    wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
    return wxMpService;
  }

  @Bean
  public WxMpConfigStorage wxMpConfigStorage(){
    WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
    wxMpConfigStorage.setAppId(weChatAccountConfig.getMpAppId());
    wxMpConfigStorage.setSecret(weChatAccountConfig.getMpAppSecret());
    return wxMpConfigStorage;
  }
}