package com.imooc.converter;

import com.imooc.dataobject.OrderMaster;
import com.imooc.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 描述: 订单主表转换成订单DTO
 *
 * @author LIULE9
 * @create 2018-10-10 7:33 PM
 */
public class OrderMaster2OrderDTOConverter {

  private static OrderDTO convert(OrderMaster orderMaster) {
    OrderDTO orderDTO = new OrderDTO();

    BeanUtils.copyProperties(orderMaster, orderDTO);

    return orderDTO;
  }

  public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
    return orderMasterList.stream().map(OrderMaster2OrderDTOConverter::convert).collect(Collectors.toList());
  }

}