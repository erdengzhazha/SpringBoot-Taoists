package com.ovopark.tao.java.tool.easyexcle.read.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * String and string converter
 */
public class CustomStringStringConverter implements Converter<String> {
  @Override
  public Class supportJavaTypeKey() {
    return String.class;
  }

  @Override
  public CellDataTypeEnum supportExcelTypeKey() {
    return CellDataTypeEnum.STRING;
  }

  /**
   * 这里读的时候会调用
   *
   * @param cellData  读取到的字符串
   *            NotNull
   * @param contentProperty
   *            Nullable
   * @param globalConfiguration
   *            NotNull
   * @return
   */
  @Override
  public String convertToJavaData(CellData cellData, ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) {
    return "自定义：" + cellData.getStringValue() + "内容属性: "+contentProperty + "GlobalConfiguration: "+ globalConfiguration;
  }

  /**
   * 这里是往excel写的时候调用 <b>读excel不用管</b>
   *
   * @param value
   *            NotNull
   * @param contentProperty
   *            Nullable
   * @param globalConfiguration
   *            NotNull
   * @return
   */
  @Override
  public CellData convertToExcelData(String value, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
    return new CellData(value);
  }

}
