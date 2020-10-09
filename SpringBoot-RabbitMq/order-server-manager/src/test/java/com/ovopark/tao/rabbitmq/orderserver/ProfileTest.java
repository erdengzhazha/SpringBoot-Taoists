package com.ovopark.tao.rabbitmq.orderserver;

import com.ovopark.tao.rabbitmq.orderserver.config.RabbitMqProfile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = OrderApplication.class)
public class ProfileTest {
  @Autowired
  RabbitMqProfile rabbitMqProfile;

  @Test
  public void r(){
    System.out.println(rabbitMqProfile.getHost());
  }
}
