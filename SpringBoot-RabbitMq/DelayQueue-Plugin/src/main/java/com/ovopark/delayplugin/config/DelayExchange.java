package com.ovopark.delayplugin.config;
import org.springframework.amqp.core.AbstractExchange;
import java.util.Map;

/**
 * 继承抽象的交换机
 * @author Xiu_Er 13813641925@163.com
 * @Date 2020年11月28号 下午 5:59
 */
public class DelayExchange extends AbstractExchange {
  public static final DelayExchange DEFAULT = new DelayExchange("");

  public DelayExchange(String name) {
    super(name);
  }

  public DelayExchange(String name, boolean durable, boolean autoDelete) {
    super(name, durable, autoDelete);
  }

  public DelayExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments) {
    super(name, durable, autoDelete, arguments);
  }

  /**
   * 交换机的Type
   * @return
   */
  public final String getType() {
    //返回延迟队列插件要求的类型
    return "x-delayed-message";
  }
}