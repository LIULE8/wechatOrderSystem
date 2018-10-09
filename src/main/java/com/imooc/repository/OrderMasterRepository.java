package com.imooc.repository;

import com.imooc.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 描述: 订单主表
 *
 * @author LIULE9
 * @create 2018-10-09 9:33 AM
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {

  Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);


}