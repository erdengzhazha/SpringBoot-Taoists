package com.ovopark.tao.rabbitmq.orderserver.mapper;

import com.ovopark.tao.rabbitmq.orderserver.entity.po.OrderDetailPO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository //告诉Spring已经实现了Mapper
public interface OrderDetailMapper {

  @Insert("INSERT INTO order (status,deliveryman_id,settlement_id,reward_id,date,address,account_id,product_id,price)" +
    "VALUES(#{status},#{deliverymanId},#{settlementId},#{rewardId},#{date},#{address},#{accountId},#{productId},#{price})")
  @Options(useGeneratedKeys = true,keyColumn = "id")
  int insert(OrderDetailPO orderDetailPO);

  @Update("UPDATE order SET status=#{status},deliveryman_id=#{deliverymanId},settlement_id=#{settlementId},reward_id=#{rewardId},date=#{date},address=#{address},account_id=#{accountId},product_id=#{productId},price=#{price}" +
    "WHERE id=#{id}")
  int update(OrderDetailPO orderDetailPO);

  @Select("SELECT id,status,deliveryman_id deliverymanId,settlement_id settlementId,reward_id rewardId,date,address,account_id accountId,product_id productId,price" +
    "FROM order WHERE id=#{id}")
  OrderDetailPO selectOrder(Integer id);
}
