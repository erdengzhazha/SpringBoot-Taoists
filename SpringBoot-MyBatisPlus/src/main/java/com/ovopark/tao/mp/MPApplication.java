package com.ovopark.tao.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.ovopark.tao.mp.mapper")
public class MPApplication {
  public static void main(String[] args) {
    SpringApplication.run(MPApplication.class,args);
  }
}
