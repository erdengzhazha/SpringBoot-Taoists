package com.ovopark.tao.nacos.demo01.user;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@NacosPropertySource(dataId = "user", groupId = "USER_GROUP", autoRefreshed = true)
@SpringBootApplication
public class UserApplication {

  public static void main(String[] args){
    SpringApplication.run(UserApplication.class,args);
  }
}
