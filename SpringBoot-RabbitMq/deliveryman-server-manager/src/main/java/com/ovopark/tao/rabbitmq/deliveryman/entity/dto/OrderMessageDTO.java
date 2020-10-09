package com.ovopark.tao.rabbitmq.deliveryman.entity.dto;
import com.ovopark.tao.rabbitmq.deliveryman.entity.enummeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMessageDTO {
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 订单状态
     */
    private OrderStatus status;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 骑手id
     */
    private Integer deliverymanId;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 账户id
     */
    private Integer accountId;
    /**
     *
     */
    private Integer settlementId;
    private Integer rewardId;
    private BigDecimal rewardAmount;
    private Boolean confirmed;
}
