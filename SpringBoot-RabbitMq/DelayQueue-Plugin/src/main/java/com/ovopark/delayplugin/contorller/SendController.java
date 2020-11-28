package com.ovopark.delayplugin.contorller;

import com.alibaba.fastjson.JSON;
import com.ovopark.delayplugin.entity.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class SendController {

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @RequestMapping("/send")
  public String send(Order order){
    byte[] messageBodyBytes = JSON.toJSON(order).toString().getBytes();
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
    messageProperties.setHeader("__TypeId__","com.ovopark.delayplugin.entity.Order");
    //发送延迟时间
    messageProperties.setHeader("x-delay",order.getTime());
    Message message = new Message(messageBodyBytes,messageProperties);
    CorrelationData correlationData = new CorrelationData();
    correlationData.setId("name");
    rabbitTemplate.send("delay-exchange-test","delay-key",message,correlationData);
    System.out.println("消息发送了"+new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date()));
    return "单号"+order.getName()+"价格"+order.getPrice()+"延迟时间"+order.getTime();
  }
}
