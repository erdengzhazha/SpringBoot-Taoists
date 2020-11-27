package com.ovopark.rabbit.service;
/**
 * @Description 声明服务
 * @author  Xiu_Er 13813641925@163.com
 * @Date 2020年10月15号 下午7:56
 */
public interface DeclareService {

  /**
   * 声明延迟队列套件
   * @param projectName
   * @return
   */
  public String declareDelayPackage(String projectName);
}
