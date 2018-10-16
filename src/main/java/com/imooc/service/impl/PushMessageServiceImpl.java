package com.imooc.service.impl;

import com.imooc.config.WeChatAccountConfig;
import com.imooc.dto.OrderDTO;
import com.imooc.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 描述: 推送消息
 *
 * @author LIULE9
 * @create 2018-10-15 6:47 PM
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

  private final WxMpService wxMpService;

  private final WeChatAccountConfig weChatAccountConfig;

  @Autowired
  public PushMessageServiceImpl(WxMpService wxMpService, WeChatAccountConfig weChatAccountConfig) {
    this.wxMpService = wxMpService;
    this.weChatAccountConfig = weChatAccountConfig;
  }

  @Override
  public void orderStatus(OrderDTO orderDTO) {
    WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
    wxMpTemplateMessage.setTemplateId(weChatAccountConfig.getTemplateId().get("orderStatus"));
    wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());
    List<WxMpTemplateData> datas = Arrays.asList(
      new WxMpTemplateData("first","亲，记得收货"),
      new WxMpTemplateData("keyword1","亲，微信点餐"),
      new WxMpTemplateData("keyword2","18819654231"),
      new WxMpTemplateData("keyword3",orderDTO.getOrderId()),
      new WxMpTemplateData("keyword4",orderDTO.getOrderStatusEnum().getMessage()),
      new WxMpTemplateData("keyword5","￥" + orderDTO.getOrderAmount()),
      new WxMpTemplateData("keyword6","欢迎再次光临!")
    );

    wxMpTemplateMessage.setData(datas);

    try {
      wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
    } catch (WxErrorException e) {
      log.error("【微信模板消息】 发送失败, {}", e);
    }
  }
}