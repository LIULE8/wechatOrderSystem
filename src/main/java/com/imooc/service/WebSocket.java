package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 描述: WebSocket
 *
 * @author LIULE9
 * @create 2018-10-15 7:18 PM
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

  private Session session;


  private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

  @OnOpen
  public void onOpen(Session session) {
    this.session = session;
    webSocketSet.add(this);
    log.info("【webSocket消息】 有新的连接, 总数:{}", webSocketSet.size());
  }

  @OnClose
  public void onClose() {
    webSocketSet.remove(this);
    log.info("【webSocket消息】 连接断开, 总数:{}", webSocketSet.size());
  }

  @OnMessage
  public void onMessage(String message) {
    log.info("【webSocket消息】 收到客户端发来的消息:{}", message);
  }

  public void sendMessage(String message) {
    log.info("【webSocket消息】 广播消息,message={}", message);
    for (WebSocket webSocket : webSocketSet) {
      try {
        webSocket.session.getBasicRemote().sendText(message);
      } catch (IOException e) {
        log.error("【webSocket消息】 广播异常={}", e);
      }
    }
  }

}