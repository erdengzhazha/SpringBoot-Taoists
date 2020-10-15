package com.ovopark.tao.rabbitmq.consumer.config;

import com.ovopark.tao.rabbitmq.common.config.rabbit.RabbitMqConfig;
import com.ovopark.tao.rabbitmq.consumer.service.MessageService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.ClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
public class RabbitConfig {

  @Autowired
  private MessageService messageService;

  /**
   * 注入一个交换机
   * @return
   */
  @Bean
  public Exchange cwExchange(){

    return new DirectExchange("exchange.boot.cw.delivery",true,false,null);
  }

  /**
   * 注入一个queue
   * @return
   */
  @Bean
  public Queue queue(){
    Map<String,Object> map = new HashMap<String,Object>(16);
    map.put("x-message-ttl",15000);
    //map.put("x-dead-letter-exchange","dlx.exchange");
    return new Queue("queue.boot.cw.delivery",true,false,false,map);
  }

  /**
   * 注入 绑定
   * @return
   */
  @Bean
  public Binding binding(){
    Binding binding = new Binding("queue.boot.cw.delivery", Binding.DestinationType.QUEUE,"exchange.boot.cw.delivery","dalay.delivery",null);
    return binding;
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

  @Bean
  public SimpleMessageListenerContainer messageListenerContainer(@Autowired ConnectionFactory connectionFactory){
    SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
    messageListenerContainer.setQueueNames("queue.boot.cw"); //监听多个队列
    messageListenerContainer.setConcurrentConsumers(3);  //限制消费者个数 3 个
    messageListenerContainer.setMaxConcurrentConsumers(5);  //最大的并发梁 5
//    messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.AUTO); //ACK
//    messageListenerContainer.setMessageListener(new MessageListener() {
//      @Override
//      public void onMessage(Message message) {
//        log.info("message:{}",message);
//      }
//    });

    //messageListenerContainer.setAcknowledgeMode(AcknowledgeMode.MANUAL); //ACK
//    messageListenerContainer.setMessageListener(new ChannelAwareMessageListener() {
//      @Override
//      public void onMessage(Message message, Channel channel) throws Exception {
//        log.info("message:{}",message);
//        messageService.handleMessage(message.getBody());
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
//      }
//    });
    messageListenerContainer.setPrefetchCount(1);
    Map<String,String> methodMap = new HashMap<>(16);
    methodMap.put("queue.boot.cw","handleMessageCW");
    MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageService);
    Jackson2JsonMessageConverter jackson2JsonMessageConverter = new Jackson2JsonMessageConverter();
    jackson2JsonMessageConverter.setClassMapper(new ClassMapper() {
      @Override
      public void fromClass(Class<?> aClass, MessageProperties messageProperties) {

      }

      @Override
      public Class<?> toClass(MessageProperties messageProperties) {
        return RabbitMqConfig.class;
      }
    });
    messageListenerAdapter.setMessageConverter(jackson2JsonMessageConverter);
    messageListenerAdapter.setQueueOrTagToMethodName(methodMap);
    messageListenerContainer.setMessageListener(messageListenerAdapter);
    return messageListenerContainer;
  }


}
