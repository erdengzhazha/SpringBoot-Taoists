package com.ovopark.tao.rabbitmq.orderserver.config;

import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.rabbitmq.client.Return;
import com.rabbitmq.client.ReturnCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitConfig {
  /**
   * 注入一个交换机
   * @return
   */
  @Bean
  public Exchange cwExchange(){
    return new DirectExchange("exchange.boot.cw",true,false,null);
  }

  /**
   * 注入一个queue
   * @return
   */
  @Bean
  public Queue queue(){
    Map<String,Object> map = new HashMap<String,Object>(16);
    map.put("x-message-ttl",15000);
    map.put("x-dead-letter-exchange","dlx.exchange");
    return new Queue("queue.boot.cw",true,false,false,map);
  }

  @Bean
  public ConnectionFactory connectionFactory(){
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
    cachingConnectionFactory.setHost(RabbitMqConfig.HOST);
    cachingConnectionFactory.setPort(RabbitMqConfig.PORT);
    cachingConnectionFactory.setUsername(RabbitMqConfig.USERNAME);
    cachingConnectionFactory.setPassword(RabbitMqConfig.PASSWORD);
    cachingConnectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
    cachingConnectionFactory.setPublisherReturns(true);
    cachingConnectionFactory.createConnection(); //主动使用,直接加载
    return cachingConnectionFactory;
  }

  /**
   * 懒加载模式，只有factory在使用的时候才声明
   * @param connectionFactory
   * @return
   */
  @Bean
  public RabbitAdmin rabbitAdmin(@Autowired ConnectionFactory connectionFactory){
    RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
    rabbitAdmin.setAutoStartup(true); //自动声明交换机、queue和binding
    return rabbitAdmin;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(@Autowired ConnectionFactory connectionFactory){
    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMandatory(true); //打开
    rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
      log.info("message:{},replyCode:{},replyText:{},exchange:{},routingKey:{}",message,replyCode,replyText,exchange,routingKey)
    );
    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
      log.info("CorrelationData:{},ack:{},cause:{}",correlationData,ack,cause)
    );
    return rabbitTemplate;
  }






  //被Autowired注解在SpringBoot启动的时候自动启动
//  @Autowired
//  public void initRabbit(){
//    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//    cachingConnectionFactory.setHost(RabbitMqConfig.HOST);
//    cachingConnectionFactory.setPort(RabbitMqConfig.PORT);
//    cachingConnectionFactory.setUsername("xing");
//    cachingConnectionFactory.setPassword("123456");
//    RabbitAdmin rabbitAdmin = new RabbitAdmin(cachingConnectionFactory);
//    Exchange cwExchange = new DirectExchange("exchange.boot.cw",true,false,null);
//    rabbitAdmin.declareExchange(cwExchange);
//    Map<String,Object> map = new HashMap<String,Object>(16);
//    map.put("x-message-ttl",15000);
//    map.put("x-dead-letter-exchange","dlx.exchange");
//    Queue queue = new Queue("queue.boot.cw",true,false,false,map);
//    rabbitAdmin.declareQueue(queue);
//    Binding binding = new Binding("", Binding.DestinationType.QUEUE,"","",null);
//  }
}
