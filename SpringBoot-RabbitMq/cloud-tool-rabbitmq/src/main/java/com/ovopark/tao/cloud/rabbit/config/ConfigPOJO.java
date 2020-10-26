package com.ovopark.tao.cloud.rabbit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>RabbitMq 常量</p>
 * @author Xiu_Er 1381361925@163.com
 * @Date 2020年10月24号 下午 4:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "cloud.rabbit" ,ignoreInvalidFields = true)
public class ConfigPOJO implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * RabbitMq 地址
   */
  private String host ;

  /**
   * RabbitMq 端口
   */
  private Integer port ;

  /**
   * RabbitMq 用户名
   */
  private String username ;

  /**
   * RabbitMq 密码
   */
  private String password ;

}
