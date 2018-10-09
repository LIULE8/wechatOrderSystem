package com.imooc.repository;

import com.imooc.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述: 订单详情
 *
 * @author LIULE9
 * @create 2018-10-09 9:35 AM
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

  List<OrderDetail> findByOrderId(String orderId);
}