package com.ovopark.tao.rabbitmq.orderserver.entity.po;

import com.ovopark.tao.rabbitmq.orderserver.entity.enummeration.OrderStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单实体类
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailPO {
  /**
   * 订单id
   */
  private Integer id;
  /**
   * 订单状态
   */
  private OrderStatusEnum status;
  /**
   * 地址
   */
  private String address;
  /**
   * 用户id
   */
  private Integer accountId;
  /**
   * 产品id
   */
  private Integer productId;
  /**
   * 骑手id
   */
  private Integer deliverymanId;
  /**
   * 结算id
   */
  private Integer settlementId;
  /**
   * 积分奖励id
   */
  private Integer rewardId;
  /**
   * 价格
   */
  private BigDecimal price;
  /**
   * 时间
   */
  private Date date;
}
