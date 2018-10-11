package com.imooc.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * 描述: json序列化时，时间类型转换成长整型
 *
 * @author LIULE9
 * @create 2018-10-11 10:09 AM
 */
public class Date2LongSerializer extends JsonSerializer<Date> {

  @Override
  public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeNumber(date.getTime() / 1000);
  }
}