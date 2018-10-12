package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Slf4j
public class PayServiceImplTest {

  @Autowired
  private PayService payService;

  @Test
  public void create() {
    OrderDTO orderDTO = new OrderDTO();
    payService.create(orderDTO);
  }
}