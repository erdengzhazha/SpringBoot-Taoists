package com.ovopark.tao.feign;

import java.io.Serializable;

/**
 *
 * @ClassName: EsBaseResultVO
 * @Description: TODO(es 返回对象)
 * @author Remiel_Mercy xuefei_fly@126.com
 * @date  2019年1月2日 下午5:01:37
 *
 */
public class EsBaseResultVO implements Serializable {

  private static final long serialVersionUID = 6428426510988278575L;

  private Boolean isError;

  private Integer code;

  private Object data;

  private String message;

  public Boolean getIsError() {
    return isError;
  }

  public void setIsError(Boolean isError) {
    this.isError = isError;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }





}
