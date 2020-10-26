package com.ovopark.tao.cloud.rabbit.core.service;

import org.springframework.lang.Nullable;

import java.util.Map;

/**
 * <p>RabbitMq的管理服务</p>
 * @author Xiu_Er 13813641925@163.com
 * @since 1.0
 * @date 2020年10月26号 下午1:48
 */
public interface ManagementService {

  //------------------ 声明操作 --------------------

  /**
   * <p>声明普通Topic交换机,也叫通配符交换机</p>
   * @apiNote 此接口声明一个Topic类型的交换机
   *          特点: 可以写通配符形式的 route Key ,去匹配Queue
   *              *（星号）：可以（只能）匹配一个单词
   *              #（井号）：可以匹配多个单词（或者零个）
   * @param name 名字
   * @param durable 是否持久化
   * @param autoDelete 是否自动删除
   * @return void
   */
  void declareTopicExchange(String name, boolean durable, boolean autoDelete);

  /**
   * <p>声明普通Direct交换机</p>
   * @param name 名字
   * @param durable 是否持久化
   * @param autoDelete 是否自动删除
   */
  void declareDirectExchange(String name, boolean durable, boolean autoDelete);

  /**
   * <p>声明一个普通Queue</>
   * @param name 名字
   * @param durable 是否持久化
   * @param exclusive 是否独立
   *                  特点:
   *                  只对首次声明它的连接（Connection）可见
   *                  会在其连接断开的时候自动删除。
   * @param autoDelete 是否自动删除
   */
  void declareQueue(String name, boolean durable, boolean exclusive, boolean autoDelete);

  /**
   * <p>声明一个TTL时效的队列</p>
   * @apiNote 注意这里的TTL是指队列中的所有消息统一过期时间， 而不是队列过期时间
   * @param name 名字
   * @param durable 是否持久化
   * @param exclusive 是否独立
   *                  特点:
   *                      只对首次声明它的连接（Connection）可见
   *                      会在其连接断开的时候自动删除。
   * @param autoDelete 是否自动删除
   * @param time 时效时间
   */
  void declareTTLQueue(String name, boolean durable, boolean exclusive, boolean autoDelete,Long time);

  /**
   * <p>声明一个Queue,死信转发给交换机</p>
   * @param name 名字
   * @param durable 是否持久化
   * @param exclusive 是否独立
   *                  特点:
   *                      只对首次声明它的连接（Connection）可见
   *                      会在其连接断开的时候自动删除。
   * @param autoDelete 是否自动删除
   * @param exchangeName 死信交换机
   */
  void declareDLQueue(String name, boolean durable, boolean exclusive, boolean autoDelete,String exchangeName);

  //------------------ 绑定操作 ---------------------

  /**
   *
   * @param queueName 队列的名字
   * @param exchangeName 交换机名字
   * @param routingKey 路由键
   * @param arguments 附加参数可以为空
   */
  void declareBinding(String queueName, String exchangeName, String routingKey, Map<String, Object> arguments);




}
