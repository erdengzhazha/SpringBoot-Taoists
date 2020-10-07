package com.ovopark.tao.rabbitmq.orderserver.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateVO {
  /**
   * 用户id
   */
  private Integer accountId;
  /**
   * 地址
   */
  private String address;
  /**
   * 产品id
   */
  private Integer productId;
}
