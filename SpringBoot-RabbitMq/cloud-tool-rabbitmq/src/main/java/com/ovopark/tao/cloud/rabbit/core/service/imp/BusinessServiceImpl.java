package com.ovopark.tao.cloud.rabbit.core.service.imp;

import com.alibaba.fastjson.JSON;
import com.ovopark.tao.cloud.rabbit.core.service.BusinessService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BusinessServiceImpl implements BusinessService {
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Override
  public void send(Object o,String exchangeName,String routingKey ,String messageId) {
    Message message = new Message(JSON.toJSONString(o).getBytes(),null);
    CorrelationData correlationData = new CorrelationData();
    correlationData.setId(messageId);
    //延时结单信息发送
    rabbitTemplate.send("exchange.workorder","delay.workorder",message,correlationData);
  }

  @Override
  public void sendTTL(Object o, String exchangeName, String routingKey, String messageId, Long time) {
    MessageProperties messageProperties = new MessageProperties();
    Message message = new Message(JSON.toJSONString(o).getBytes(),null);
    messageProperties.setExpiration(time.toString());//定义一个统一的失效时间
    CorrelationData correlationData = new CorrelationData();
    correlationData.setId(messageId);
    //延时结单信息发送
    rabbitTemplate.send("exchange.workorder","delay.workorder",message,correlationData);
  }
}
