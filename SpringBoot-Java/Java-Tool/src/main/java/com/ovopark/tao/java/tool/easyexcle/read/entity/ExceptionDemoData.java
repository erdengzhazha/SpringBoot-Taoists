package com.ovopark.tao.java.tool.easyexcle.read.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ExceptionDemoData {

  /**
   * 用日期去接字符串肯定会报错
   */
  private Date date;
}
