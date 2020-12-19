package com.ovopark.tao.rabbitmq.common.config.rabbit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * <p>RabbitMqConfig 配置信息</p>
 * @author Xiu_Er
 * @Date 2020年10月9号 下午8:16
 */
@Data
public class RabbitMqConfig implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * RabbitMq 地址
   */
  public static final String HOST = "192.168.48.129";

  /**
   * RabbitMq 端口
   */
  public static final Integer PORT = 5671;

  public static final String USERNAME = "guest";

  public static final String PASSWORD = "guest";
}
