package com.ovopark.tao.valid.utils;

/**
 * 统一返回格式
 *
 * @param <T>
 */
public class ResultVoUtil<T> {

  /**
   * 代码
   */
  private Integer code;
  /**
   * 数据
   */
  private T date;
  /**
   * 信息
   */
  private String message;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public T getDate() {
    return date;
  }

  public void setDate(T date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}
