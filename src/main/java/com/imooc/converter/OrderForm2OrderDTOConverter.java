package com.imooc.converter;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.imooc.dataobject.OrderDetail;
import com.imooc.dto.OrderDTO;
import com.imooc.enums.ResultEnum;
import com.imooc.exception.SellException;
import com.imooc.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 订单表单对象转换成OrderDTO
 *
 * @author LIULE9
 * @create 2018-10-10 8:44 PM
 */
@Slf4j
public class OrderForm2OrderDTOConverter {


  public static OrderDTO convert(OrderForm orderForm) {
    Gson gson = new Gson();

    OrderDTO orderDTO = new OrderDTO();
    orderDTO.setBuyerName(orderForm.getName());
    orderDTO.setBuyerPhone(orderForm.getPhone());
    orderDTO.setBuyerAddress(orderForm.getAddress());
    orderDTO.setBuyerOpenid(orderForm.getOpenid());
    List<OrderDetail> orderDetailList;
    try {
      orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
      }.getType());
    } catch (JsonSyntaxException e) {
      log.error("【对象转换】错误, string={}", orderForm.getItems());
      throw new SellException(ResultEnum.PARAM_ERROR);
    }

    orderDTO.setOrderDetailList(orderDetailList);

    return orderDTO;
  }

}