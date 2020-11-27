package com.ovopark.rabbit;

import com.alibaba.fastjson.JSON;
import com.ovopark.rabbit.config.RabbitProperties;
import com.ovopark.rabbit.entity.Order;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RabbitApplication {

  public static void main(String[] args) {
    SpringApplication.run(RabbitApplication.class, args);
//    RabbitProperties rabbitProperties =(RabbitProperties) run.getBean("rabbitProperties");
//    RabbitTemplate rabbitTemplate =(RabbitTemplate) run.getBean("rabbitTemplate");
//
//    MessageProperties messageProperties = new MessageProperties();
//    messageProperties.setExpiration(rabbitProperties.getMessageTTL().toString());
//    messageProperties.setContentType("application/json");
//    messageProperties.getHeaders().put("__TypeId__","com.ovopark.rabbit.entity.Order");
//    Order order = new Order();
//    order.setOrderNo("TN1204451");
//    order.setPrice(256.21);
//    Message message = new Message(JSON.toJSON(order).toString().getBytes(),messageProperties);
//    CorrelationData correlationData = new CorrelationData();
//
//    correlationData.setId(order.getOrderNo());
//
//    rabbitTemplate.send("exchange.workorder","delay.workorder",message,correlationData);
  }
}
