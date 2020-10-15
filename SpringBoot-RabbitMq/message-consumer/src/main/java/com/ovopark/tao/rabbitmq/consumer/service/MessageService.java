package com.ovopark.tao.rabbitmq.consumer.service;

import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.ovopark.tao.rabbitmq.consumer.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageService {

  public void handleMessageCW(RabbitMqConfig rabbitMqConfig){
    log.info("messageBody:{}",rabbitMqConfig);
  }
}
