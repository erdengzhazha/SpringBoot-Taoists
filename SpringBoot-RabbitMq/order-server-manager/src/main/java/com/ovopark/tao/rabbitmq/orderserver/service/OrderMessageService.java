package com.ovopark.tao.rabbitmq.orderserver.service;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息处理相关逻辑
 */
public class OrderMessageService {
  /**
   * 声明消息队列，交换机，绑定，消息的处理
   */
  public void handleMessage() throws IOException, TimeoutException {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost("49.233.5.107");
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

}
