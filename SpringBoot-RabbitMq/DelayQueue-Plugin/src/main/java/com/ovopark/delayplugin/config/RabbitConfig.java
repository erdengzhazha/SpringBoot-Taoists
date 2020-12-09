package com.ovopark.delayplugin.config;

import com.ovopark.delayplugin.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  RabbitMq 的配置文件
 * @author Xiu_Er 1381364195@163.com
 * @Date 2020年11月28日 下午 4：08
 */
@Slf4j
@Configuration
public class RabbitConfig {
  /**
   * 连接工厂
   * @return
   */
  @Bean
  public ConnectionFactory connectionFactory(@Autowired RabbitMqProperties rabbitMqProperties){
    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
    cachingConnectionFactory.setHost(rabbitMqProperties.getHost());
    cachingConnectionFactory.setPort(rabbitMqProperties.getPort());
    cachingConnectionFactory.setUsername(rabbitMqProperties.getUsername());
    cachingConnectionFactory.setPassword(rabbitMqProperties.getPassword());
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
    rabbitTemplate.setMandatory(true); //打开,以上exchange, queue, binding,自动装配
    //rabbitTemplate.setMessageConverter(jsonMessageConverter());
    rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) ->
      log.info("回拨信息message:{},replyCode:{},replyText:{},exchange:{},routingKey:{}",message,replyCode,replyText,exchange,routingKey)
    );
    rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
      log.info("确认回拨CorrelationData:{},ack:{},cause:{}",correlationData,ack,cause)
    );
    return rabbitTemplate;
  }


  @Autowired
  private MessageService messageService;
  private static Logger log =  LoggerFactory.getLogger(RabbitConfig.class);
  static Map<String,String> methodMap = new HashMap<>();
  /**
   * 配置监听
   * @param connectionFactory
   * //@param messageService
   * @return
   */
  @Bean
  public SimpleMessageListenerContainer messageListenerContainer(@Autowired ConnectionFactory connectionFactory){
    SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);

    messageListenerContainer.setQueueNames("queue.delay.workOrder");
    messageListenerContainer.setConcurrentConsumers(3);
    messageListenerContainer.setMaxConcurrentConsumers(5);
    messageListenerContainer.setPrefetchCount(1);

    MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageService);
    methodMap.put("queue.smart.devv","handleOrderMessage");

    messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
    messageListenerAdapter.setQueueOrTagToMethodName(methodMap);
    messageListenerContainer.setMessageListener(messageListenerAdapter);

    return messageListenerContainer;
  }
}
