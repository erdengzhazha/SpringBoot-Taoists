package com.ovopark.delayplugin;

import com.alibaba.fastjson.JSON;
import com.ovopark.delayplugin.config.DelayExchange;
import com.ovopark.delayplugin.config.RabbitMqProperties;
import com.ovopark.delayplugin.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class DelayPluginDemo {

  @Autowired
  private RabbitAdmin rabbitAdmin;

  @Autowired
  private RabbitMqProperties rabbitMqProperties;

  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  public void initExchange(){
    Map<String, Object> map = new HashMap<>();
    map.put("x-delayed-type", "direct");
    Exchange exchange = new DelayExchange("delay-exchange-test",true,false,map);
    rabbitAdmin.declareExchange(exchange);
  }

  @Test
  public void initQueue(){
    Queue queue = new Queue("test-queue", true, false, false, null);
    rabbitAdmin.declareQueue(queue);
    Binding binding = new Binding("test-queue", Binding.DestinationType.QUEUE,"delay-exchange-test","delay-key",null);
    rabbitAdmin.declareBinding(binding);
  }

  @Test
  public void send() throws UnsupportedEncodingException {
//    byte[] messageBodyBytes = "delayed payload".getBytes("UTF-8");
    Order order = new Order();
    order.setName("order的姓名");
    order.setPrice(12.525);
    byte[] messageBodyBytes = JSON.toJSON(order).toString().getBytes();
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
    messageProperties.setHeader("__TypeId__","com.ovopark.delayplugin.entity.Order");
    messageProperties.setHeader("x-delay",5000);
    Message message = new Message(messageBodyBytes,messageProperties);
    CorrelationData correlationData = new CorrelationData();
    correlationData.setId("name");
    rabbitTemplate.send("delay-exchange-test","delay-key",message,correlationData);
    System.out.println("消息发送了"+new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date()));
    try {
      Thread.sleep(500000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
