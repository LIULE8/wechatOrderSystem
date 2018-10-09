package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class OrderDetailRepositoryTest {

  @Autowired
  private OrderDetailRepository orderDetailRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void should_return_order_detail_list_when_input_a_order_id() {
    OrderDetail orderDetail = new OrderDetail();
    orderDetail.setOrderId("1");
    orderDetail.setDetailId("1");
    orderDetail.setProductId(anyString());
    orderDetail.setProductName(anyString());
    orderDetail.setProductQuantity(anyInt());
    orderDetail.setProductPrice(new BigDecimal(1));
    entityManager.persistAndFlush(orderDetail);

    List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1");

    Assert.assertNotEquals(0, orderDetailList.size());
  }
}