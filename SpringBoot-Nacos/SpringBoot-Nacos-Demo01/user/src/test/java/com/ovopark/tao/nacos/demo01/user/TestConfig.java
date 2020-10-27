package com.ovopark.tao.nacos.demo01.user;

import com.ovopark.tao.nacos.demo01.user.Config.UserConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = UserApplication.class)
public class TestConfig {

  @Autowired
  private UserConfig userConfig;

  @Test
  public void testUserConfig(){
    System.out.println(userConfig.getStatus());
  }
}
