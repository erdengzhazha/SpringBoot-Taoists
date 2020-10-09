package com.ovopark.tao.rabbitmq.deliveryman.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.ovopark.tao.rabbitmq.deliveryman.entity.dto.OrderMessageDTO;
import com.ovopark.tao.rabbitmq.deliveryman.entity.enummeration.DeliverymanStatus;
import com.ovopark.tao.rabbitmq.deliveryman.entity.po.DeliverymanPO;
import com.ovopark.tao.rabbitmq.deliveryman.mapper.DeliverymanMapper;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class OrderMessageService {

    @Autowired
    private DeliverymanMapper deliverymanMapper;
    @Autowired
    private ObjectMapper objectMapper;

    DeliverCallback deliverCallback = (consumerTag, message) -> {
        String messageBody = new String(message.getBody());
        log.info("deliverCallback:messageBody:{}", messageBody);
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try {
            OrderMessageDTO orderMessageDTO = objectMapper.readValue(messageBody,
                    OrderMessageDTO.class);
            List<DeliverymanPO> deliverymanPOS = deliverymanMapper.selectDeliverymanByStatus(DeliverymanStatus.AVALIABIE);
            orderMessageDTO.setDeliverymanId(deliverymanPOS.get(0).getId());
            log.info("onMessage:restaurantOrderMessageDTO:{}", orderMessageDTO);

            try (Connection connection = connectionFactory.newConnection();
                 Channel channel = connection.createChannel()) {
                String messageToSend = objectMapper.writeValueAsString(orderMessageDTO);
                channel.basicPublish("exchange.order.restaurant", "key.order", null, messageToSend.getBytes());
            }
        } catch (JsonProcessingException | TimeoutException e) {
            e.printStackTrace();
        }
    };

    @Async
    public void handleMessage() throws IOException, TimeoutException, InterruptedException {
        log.info("start linstening message");
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST);
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(
                    "exchange.order.deliveryman",
                    BuiltinExchangeType.DIRECT,
                    true,
                    false,
                    null);

            channel.queueDeclare(
                    "queue.deliveryman",
                    true,
                    false,
                    false,
                    null);

            channel.queueBind(
                    "queue.deliveryman",
                    "exchange.order.deliveryman",
                    "key.deliveryman");


            channel.basicConsume("queue.deliveryman", true, deliverCallback, consumerTag -> {
            });
            while (true) {
                Thread.sleep(100000);
            }
        }
    }
}

