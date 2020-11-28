package com.ovopark.delayplugin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "rabbit.single")
public class RabbitMqProperties implements Serializable {

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

  public RabbitMqProperties() {
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
