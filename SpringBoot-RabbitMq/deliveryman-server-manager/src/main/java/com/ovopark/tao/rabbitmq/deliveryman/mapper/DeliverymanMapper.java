package com.ovopark.tao.rabbitmq.deliveryman.mapper;

import com.ovopark.tao.rabbitmq.deliveryman.entity.enummeration.DeliverymanStatus;
import com.ovopark.tao.rabbitmq.deliveryman.entity.po.DeliverymanPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DeliverymanMapper {

  @Select("SELECT * FROM deliveryman WHERE status=#{deliverymanStatus}")
  List<DeliverymanPO> selectDeliverymanByStatus(DeliverymanStatus deliverymanStatus);
}
