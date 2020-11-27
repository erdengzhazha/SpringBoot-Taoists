package com.ovopark.rabbit.config;
import com.ovopark.rabbit.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description RabbitMq Spring配置
 * @author  Xiu_Er 13813641925@163.com
 * @Date 2020年10月15号 下午7:56
 */
@Configuration
public class RabbitConfig {

  private static Logger log =  LoggerFactory.getLogger(RabbitConfig.class);
  static Map<String,String> methodMap = new HashMap<>();

//  /**
//   * 注入一个订单交换机
//   * @return
//   */
//  @Bean
//  public Exchange orderExchange(){
//    return new DirectExchange("exchange.workorder",true,false,null);
//  }
//
//  /**
//   * 注入一个具有失效时间的queue
//   * @return
//   */
//  @Bean
//  public Queue orderQueue(){
//    Map<String,Object> map = new HashMap<String,Object>(8);
//    //map.put("x-message-ttl",15000);
//    map.put("x-dead-letter-exchange","dtl.exchange.workorder");
//    return new Queue("ttl.queue.workorder",true,false,false,map);
//  }
//
//  /**
//   * 绑定exchange.workorder(交换机) 和 ttl.queue.workorder (队列)
//   * @return
//   */
//  @Bean
//  public Binding bindingTTL(){
//    Binding binding = new Binding("ttl.queue.workorder", Binding.DestinationType.QUEUE,"exchange.workorder","delay.workorder",null);
//    return binding;
//  }
//
//  /**
//   * 注入工单死信交换机
//   * @return
//   */
//  @Bean
//  public Exchange dtlOrderExchange(){
//    return new TopicExchange("dtl.exchange.workorder",true,false,null);
//  }
//
//  /**
//   * 注入一个工单死信队列
//   * @return
//   */
//  @Bean
//  public Queue dtlOrderQueue(){
//    return new Queue("dtl.queue.workorder",true,false,false);
//  }
//
//  /**
//   * 绑定 dtl.exchange.workorder(交换机) 和 dtl.queue.workorder (队列)
//   * @return
//   */
//  @Bean
//  public Binding dtlOrderBinding(){
//    return new Binding("dtl.queue.workorder", Binding.DestinationType.QUEUE,"dtl.exchange.workorder","#",null);
//  }


  /**
   * 连接工厂
   * @return
   */
  @Bean
  public ConnectionFactory connectionFactory(@Autowired RabbitProperties rabbitMqProperties){
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
  /**
   * 配置监听
   * @param connectionFactory
   * //@param messageService
   * @return
   */
//  @Bean
//  public SimpleMessageListenerContainer messageListenerContainer(@Autowired ConnectionFactory connectionFactory){
//    SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
//    messageListenerContainer.setQueueNames("dtl.queue.workorder");
//    messageListenerContainer.setConcurrentConsumers(3);
//    messageListenerContainer.setMaxConcurrentConsumers(5);
//    messageListenerContainer.setPrefetchCount(1);
//    MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(messageService);
//    methodMap.put("dtl.queue.workorder","handleOrderMessage");
//    messageListenerAdapter.setMessageConverter(new Jackson2JsonMessageConverter());
//    messageListenerAdapter.setQueueOrTagToMethodName(methodMap);
//    messageListenerContainer.setMessageListener(messageListenerAdapter);
//
//    return messageListenerContainer;
//  }

}
