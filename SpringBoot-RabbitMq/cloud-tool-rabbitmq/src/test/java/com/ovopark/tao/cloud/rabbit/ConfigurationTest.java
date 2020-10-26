package com.ovopark.tao.cloud.rabbit;

import com.ovopark.tao.cloud.rabbit.config.ConfigPOJO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(classes = RabbitApplication.class)
public class ConfigurationTest {

  @Autowired
  private ConfigPOJO configPOJO;
  @Autowired
  private RabbitAdmin rabbitAdmin;
  @Autowired
  private RabbitTemplate rabbitTemplate;

  @Test
  public void testConfiguration(){
    System.out.println(configPOJO.toString());
    System.out.println("等哈恢复到健康");
  }


  @Test
  public void declareTopicExchange(){
    TopicExchange topicExchange = new TopicExchange("exchange.test01", true, false, null);
    rabbitAdmin.declareExchange(topicExchange);
  }

  /**
   *
   */
  @Test
  public void declareQueue(){
    Queue queue = new Queue("queue.test03",true,false,false,null);
    String s = rabbitAdmin.declareQueue(queue);
    log.info("Queue 打印"+s);
  }

  /**
   * 绑定交换机和Queue
   */
  @Test
  public void binding(){
    Binding binding = new Binding(
      "queue.test03",
      Binding.DestinationType.QUEUE,
      "exchange.test01",
      "key.test",
      null
    );
    rabbitAdmin.declareBinding(binding);
  }

}
