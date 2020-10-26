package com.ovopark.tao.cloud.rabbit.core.service;

public interface BusinessService {
  /**
   * 普通模式 发送消息
   * @param o
   * @param exchangeName
   * @param routingKey
   * @param messageId
   */
  void send(Object o,String exchangeName,String routingKey ,String messageId);

  /**
   * 发送TTL消息
   * @param o
   * @param exchangeName
   * @param routingKey
   * @param messageId
   * @param time
   */
  void sendTTL(Object o,String exchangeName,String routingKey ,String messageId,Long time);
}
