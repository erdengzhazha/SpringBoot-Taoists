package com.ovopark.delayplugin.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

  private String name;
  private Double price;

  private Integer time;

}
