package com.ovopark.tao.mp;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.ovopark.tao.mp.mapper")
@MapperScan(value = "com.ovopark.tao.mp.mygenerator.mapper")
@SpringBootApplication(exclude = DruidDataSourceAutoConfigure.class)
public class MPApplication {

  public static void main(String[] args) {
    SpringApplication.run(MPApplication.class,args);
  }
}
