package com.ovopark.tao.cloud.rabbit.core.service.imp;
import com.ovopark.tao.cloud.rabbit.core.service.ManagementService;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;


import java.util.HashMap;
import java.util.Map;


public class ManagementServiceImpl implements ManagementService {

  @Autowired
  RabbitAdmin rabbitAdmin;

  @Override
  public void declareTopicExchange(String name, boolean durable, boolean autoDelete) {
    TopicExchange topicExchange = new TopicExchange(name, durable, autoDelete, null);
    rabbitAdmin.declareExchange(topicExchange);
  }

  @Override
  public void declareDirectExchange(String name, boolean durable, boolean autoDelete) {
    DirectExchange directExchange = new DirectExchange(name,durable,autoDelete,null);
    rabbitAdmin.declareExchange(directExchange);
  }

  @Override
  public void declareQueue(String name, boolean durable, boolean exclusive, boolean autoDelete) {
    Queue queue = new Queue(name,durable,exclusive,autoDelete,null);
    rabbitAdmin.declareQueue(queue);
  }

  @Override
  public void declareTTLQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, Long time) {
    Map<String,Object> map = new HashMap<String,Object>(1);
    map.put("x-message-ttl",time.toString());
    Queue queue = new Queue(name,durable,exclusive,autoDelete,map);
    rabbitAdmin.declareQueue(queue);
  }

  @Override
  public void declareDLQueue(String name, boolean durable, boolean exclusive, boolean autoDelete, String exchangeName) {
    Map<String,Object> map = new HashMap<String,Object>(1);
    map.put("x-dead-letter-exchange",exchangeName);
    Queue queue = new Queue(name,durable,exclusive,autoDelete,map);
    rabbitAdmin.declareQueue(queue);
  }

  @Override
  public void declareBinding(String destination, String exchange, String routingKey, Map<String, Object> arguments){
    Binding binding = new Binding(destination,Binding.DestinationType.QUEUE,exchange,routingKey,arguments);
    rabbitAdmin.declareBinding(binding);
  }



}
