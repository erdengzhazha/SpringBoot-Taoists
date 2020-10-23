package com.ovopark.tao.java.tool.easyexcle.write.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

import java.util.Date;

/**
 * 基础数据类
 **/
@Data
@ContentRowHeight(10) //内容行高度
@HeadRowHeight(20) //头排高度
@ColumnWidth(25) //列宽
public class WidthAndHeightData {
  @ExcelProperty("字符串标题")
  private String string;
  @ExcelProperty("日期标题")
  private Date date;
  /**
   * 宽度为50
   */
  @ColumnWidth(50)
  @ExcelProperty("数字标题")
  private Double doubleData;
}