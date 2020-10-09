package com.ovopark.tao.rabbitmq.deliveryman.entity.po;

import com.ovopark.tao.rabbitmq.deliveryman.entity.enummeration.DeliverymanStatus;
import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliverymanPO {
    /**
     * 骑手id
     */
    private Integer id;
    /**
     * 骑手名字
     */
    private String name;
    /**
     * 区域
     */
    private String district;
    /**
     * 骑手状态
     */
    private DeliverymanStatus status;
    /**
     * 时间
     */
    private Date date;
}
