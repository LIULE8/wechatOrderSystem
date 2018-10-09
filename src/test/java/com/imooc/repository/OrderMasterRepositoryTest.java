package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class OrderMasterRepositoryTest {

  @Autowired
  private OrderMasterRepository orderMasterRepository;

  @Autowired
  private TestEntityManager entityManager;

  @Test
  public void should_return_a_list_order_master_when_input_a_buyer_open_id() {
    PageRequest pageRequest = PageRequest.of(0, 2);

    OrderMaster orderMaster = new OrderMaster();
    orderMaster.setBuyerOpenid("1");
    orderMaster.setOrderId(anyString());
    orderMaster.setBuyerAddress(anyString());
    orderMaster.setBuyerName(anyString());
    orderMaster.setBuyerPhone(anyString());
    orderMaster.setOrderAmount(new BigDecimal(1));
    entityManager.persistAndFlush(orderMaster);


    long totalElements = orderMasterRepository.findByBuyerOpenid("1", pageRequest).getTotalElements();

    Assert.assertEquals(1L,totalElements );

  }
}