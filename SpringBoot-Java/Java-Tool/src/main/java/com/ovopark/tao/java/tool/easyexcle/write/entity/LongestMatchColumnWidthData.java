package com.ovopark.tao.java.tool.easyexcle.write.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 基础数据类
 *
 **/
@Data
public class LongestMatchColumnWidthData {
  @ExcelProperty("字符串标题")
  private String string;
  @ExcelProperty("日期标题很长日期标题很长日期标题很长很长")
  private Date date;
  @ExcelProperty("数字")
  private Double doubleData;
}