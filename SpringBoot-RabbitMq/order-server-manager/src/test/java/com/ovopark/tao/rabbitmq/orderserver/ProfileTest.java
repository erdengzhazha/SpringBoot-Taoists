package com.ovopark.tao.rabbitmq.orderserver;

import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@SpringBootTest(classes = OrderApplication.class)
public class ProfileTest {
  //@Autowired
  //RabbitMqProfile rabbitMqProfile;

  /**
   * 测试读取配置
   */
  @Test
  public void r(){
    //System.out.println(rabbitMqProfile.getHost());
  }

  /**
   * "队列"TTL
   * @throws IOException
   * @throws TimeoutException
   */
  @Test
  public void declareRabbitMq() throws IOException, TimeoutException {
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost(RabbitMqConfig.HOST);

    //自动关闭连接，因为Connection集成了Closeable
    try (Connection connection = connectionFactory.newConnection();
         Channel channel = connection.createChannel();) {
      /**------------ 交换机声明   --------------*/
      channel.exchangeDeclare("dlx.exchange" //交换机名字
        , BuiltinExchangeType.TOPIC  //交换机类型
        , true    //持久化
        , false  //不自动删除
        , null  //不需要特殊属性
      );
      channel.queueDeclare("dlx.queue"
                            ,true,false,false,null);
      channel.queueBind("dlx.queue","dlx.exchange","#");
      /**--------- 队列声明 ----------------*/
      Map<String,Object> args = new HashMap<>(16);
      args.put("x-message-ttl",15000); //队列带着这个参数，就会清理队列中超过15秒的消息
      args.put("x-dead-letter-exchange","dlx.exchange"); //设置死信发送的 交换机
      channel.queueDeclare(
        "queue.order",
        true,
        false,
        false,
        args);
      /**-------- 队列绑定  ------------*/
//      channel.queueBind("queue.order", "exchange.order.restaurant", "key.order");
    }
  }


  /**
   * 测试"消息"TTL
   */
  @Test
  public void testTTL() throws IOException, TimeoutException {
    String message = "我是?";
    ConnectionFactory connectionFactory = new ConnectionFactory();
    connectionFactory.setHost(RabbitMqConfig.HOST);
    try(Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();){
      AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("15000").build();
      channel.basicPublish("dalay","dalay",properties,message.getBytes());

    }
  }

  @Autowired
  private RabbitTemplate rabbitTemplate;
  /**
   * 发送消息
   */
  @Test
  public void testRabbitTemplet(){
    MessageProperties messageProperties = new MessageProperties();
    messageProperties.setExpiration("15000");
    String m = "随便说点什么";
    Message message = new Message(m.getBytes(),messageProperties);
    /**
     * 消息发送， convertAndSend 不好设置过期时间
     */
    //rabbitTemplate.convertAndSend("dalay","dalay",m);
    rabbitTemplate.send("dalay","dalay",message);
  }


}
