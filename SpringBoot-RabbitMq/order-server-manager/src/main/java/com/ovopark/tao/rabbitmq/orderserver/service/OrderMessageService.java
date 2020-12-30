package com.ovopark.tao.rabbitmq.orderserver.service;
import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
//import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.ovopark.tao.rabbitmq.orderserver.entity.dto.OrderMessageDTO;
import com.ovopark.tao.rabbitmq.orderserver.entity.enummeration.OrderStatusEnum;
import com.ovopark.tao.rabbitmq.orderserver.entity.po.OrderDetailPO;
import com.ovopark.tao.rabbitmq.orderserver.mapper.OrderDetailMapper;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息处理相关逻辑
 */
@Slf4j
public class OrderMessageService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    OrderDetailMapper orderDetailMapper;
  /**
   * 声明消息队列，交换机，绑定，消息的处理
   */
  public void handleMessage() throws IOException, TimeoutException {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost(RabbitMqConfig.HOST);
    //自动关闭连接，因为Connection集成了Closeable
    try (Connection connection = connectionFactory.newConnection();
         Channel channel = connection.createChannel();){
        /**--------- restaurant(餐厅) ------------ */
        /**------------ 交换机声明   --------------*/
        channel.exchangeDeclare("exchange.order.restaurant" //交换机名字
                                , BuiltinExchangeType.DIRECT  //交换机类型
                                , true    //持久化
                                , false  //不自动删除
                                , null  //不需要特殊属性
                                );
        /**--------- 队列声明 ----------------*/
        channel.queueDeclare(
                            "queue.order",
                            true,
                            false,
                            false,
                            null);
        /**-------- 队列绑定  ------------*/
        channel.queueBind("queue.order","exchange.order.restaurant","key.order");

      /**--------- deliveryman(骑手) ------------ */
      /**------------ 交换机声明   --------------*/
      channel.exchangeDeclare("exchange.order.deliveryman" //交换机名字
        , BuiltinExchangeType.DIRECT  //交换机类型
        , true    //持久化
        , false  //不自动删除
        , null  //不需要特殊属性
      );

      /**-------- 队列绑定  ------------*/
      channel.queueBind("queue.order","exchange.order.deliveryman","key.order");

    }
  }

    DeliverCallback deliverCallback = ((consumerTag, message) -> {
        //收到消息
        String messageBody  = new String (message.getBody());
        //回发消息
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(RabbitMqConfig.HOST);
        try{
            //将消息反序列化为DTO
            OrderMessageDTO orderMessageDTO = objectMapper.readValue(messageBody, OrderMessageDTO.class);
            //从数据库中读取订单PO
            OrderDetailPO orderDetailPO = orderDetailMapper.selectOrder(orderMessageDTO.getOrderId());

            //更改订单状态
            switch (orderDetailPO.getStatus()){
                case ORDER_CREATING:
                    if(orderMessageDTO.getConfirmed() && orderMessageDTO.getPrice()!=null){
                        //说明商家的业务通了
                        orderDetailPO.setStatus(OrderStatusEnum.RESTAURANT_CONFIRMED);
                        orderDetailPO.setPrice(orderMessageDTO.getPrice());
                        int update = orderDetailMapper.update(orderDetailPO);
                        if(update>0){
                            log.info("更新成功：orderDetailPO:{}"+orderDetailPO);
                        }else{
                            log.info("更新失败: orderDetailPO:{}"+orderDetailPO);
                        }
                        try(Connection connection = connectionFactory.newConnection();
                            Channel channel = connection.createChannel();){
                            String messageToSend = objectMapper.writeValueAsString(orderMessageDTO);
                            //向棋手微服务发送消息
                            channel.basicPublish("exchange.order.deliveryman"
                                    ,"key.deliveryman"
                                    ,null
                                    ,messageToSend.getBytes());
                        }
                    }else{
                        //订单失败了
                        orderDetailPO.setStatus(OrderStatusEnum.FAILED);
                        orderDetailMapper.update(orderDetailPO);
                    }
                    break;
                case RESTAURANT_CONFIRMED:
                    break;
                case DELIVERTMAN_CONFIRMED:
                    break;
                case SETTLEMENT_CONFIRMED:
                    break;
                case ORDER_CREATED:
                    break;
                case FAILED:
                    break;
            }

        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
    });

}
