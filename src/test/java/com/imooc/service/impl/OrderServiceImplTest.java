package com.imooc.service.impl;

import com.imooc.dto.OrderDTO;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {

  @InjectMocks
  private OrderServiceImpl orderService;

  @Mock
  private OrderMasterRepository orderMasterRepository;

  @Mock
  private OrderDetailRepository orderDetailRepository;

  @Mock
  private ProductService productService;


  @Test
  public void create() {
    OrderDTO result = null;
    log.info("【创建订单】result = {}", result);
  }

  @Test
  public void findOne() {
  }

  @Test
  public void findList() {
  }

  @Test
  public void cancel() {
  }

  @Test
  public void finish() {
  }

  @Test
  public void paid() {
  }
}