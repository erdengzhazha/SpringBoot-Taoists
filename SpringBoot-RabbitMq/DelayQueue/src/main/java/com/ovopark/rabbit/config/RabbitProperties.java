package com.ovopark.rabbit.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
/**
 * @Description RabbitMq参数配置
 * @author  Xiu_Er 13813641925@163.com
 * @Date 2020年10月15号 下午7:56
 */
@Component
@ConfigurationProperties(prefix = "rabbitmq.single")
public class RabbitProperties implements Serializable {

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

  /**
   * 消息过期时间
   */
  private Long messageTTL;


  public Long getMessageTTL() {
    return messageTTL;
  }

  public void setMessageTTL(Long messageTTL) {
    this.messageTTL = messageTTL;
  }

  public RabbitProperties() {
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public Integer getPort() {
    return port;
  }

  public void setPort(Integer port) {
    this.port = port;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
