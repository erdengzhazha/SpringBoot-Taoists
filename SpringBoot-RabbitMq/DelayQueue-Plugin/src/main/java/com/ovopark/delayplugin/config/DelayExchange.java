package com.ovopark.delayplugin.config;
import org.springframework.amqp.core.AbstractExchange;
import java.util.Map;

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

  public final String getType() {
    return "x-delayed-message";
  }
}