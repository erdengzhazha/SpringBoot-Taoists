package com.ovopark.tao.rabbitmq.orderserver.entity.enummeration;

public enum OrderStatusEnum {
  /**
   * 创建中
   */
  ORDER_CREATING,
  /**
   * 餐厅已确认
   */
  RESTAURANT_CONFIRMED,
  /**
   * 骑手确认
   */
  DELIVERTMAN_CONFIRMED,
  /**
   * 已结算
   */
  SETTLEMENT_CONFIRMED,
  /**
   * 订单已创建
   */
  ORDER_CREATED,
  /**
   * 订单创建失败
   */
  FAILED;
}
