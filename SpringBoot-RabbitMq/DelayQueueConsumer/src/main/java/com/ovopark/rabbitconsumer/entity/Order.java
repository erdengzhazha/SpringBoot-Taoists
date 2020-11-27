package com.ovopark.rabbitconsumer.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单类
 */
@Data
public class Order implements Serializable {

  private String orderNo;
  private Double price;


}
