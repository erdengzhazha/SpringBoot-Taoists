package com.ovopark.tao.rabbitmq.orderserver.entity.dto;

import com.ovopark.tao.rabbitmq.orderserver.entity.enummeration.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessageDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 订单id
   */
  private Integer orderId;
  /**
   * 订单状态
   */
  private OrderStatusEnum orderStatusEnum;
  /**
   * 价格
   */
  private BigDecimal price;
  /**
   * 骑手id
   */
  private Integer deliverymanId;
  /**
   * 产品Id
   */
  private Integer productId;
  /**
   * 用户id
   */
  private Integer accountId;
  /**
   * 结算id
   */
  private Integer settlementId;
  /**
   * 积分结算id
   */
  private Integer rewardId;
  /**
   * 积分奖励数量
   */
  private Integer rewardAmount;
  /**
   * 确认
   */
  private Boolean confirmed;

}
