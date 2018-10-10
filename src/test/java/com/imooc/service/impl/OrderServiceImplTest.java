package com.imooc.service.impl;

import com.imooc.dataobject.OrderDetail;
import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import com.imooc.repository.OrderDetailRepository;
import com.imooc.repository.OrderMasterRepository;
import com.imooc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

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
  public void should_return_a_orderDTO_when_input_orderId() {
    String orderId = "110110";
    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setOrderId(orderId);
    when(orderMasterRepository.findById(orderId)).thenReturn(java.util.Optional.of(orderMaster));
    List<OrderDetail> orderDetailList = new ArrayList<>();
    orderDetailList.add(new OrderDetail());
    when(orderDetailRepository.findByOrderId(orderId)).thenReturn(orderDetailList);

    OrderDTO orderDTO = orderService.findOne(orderId);

    log.info("【查询单个订单】 result={}",orderDTO);
    Assert.assertEquals(orderId, orderDTO.getOrderId());
    Assert.assertEquals(1, orderDTO.getOrderDetailList().size());
  }

  @Test
  public void should_return_page_object_with_2_orderDTO_when_input_buyerOpenid_and_find_2_record() {

    PageRequest pageRequest = PageRequest.of(0,2);
    String buyerOpenid = "001";
    when(orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageRequest)).thenReturn(new PageImpl<OrderMaster>(
       new ArrayList<OrderMaster>(){{
          add(new OrderMaster());
          add(new OrderMaster());
       }},
        pageRequest,
        2
    ));

    Page<OrderDTO> orderDTOPage = orderService.findList(buyerOpenid, pageRequest);

    Assert.assertEquals(2,orderDTOPage.getTotalElements());

  }


  @Test
  public void paid() {
  }
}