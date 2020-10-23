package com.ovopark.tao.java.tool.easyexcle.read.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.ovopark.tao.java.tool.easyexcle.read.entity.DemoData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {

  private static final int BATCH_COUNT = 5;
  List<DemoData> list = new ArrayList<>(32);




  /**
   * 每一条数据解析，都会调用
   * @param demoData
   * @param analysisContext
   */
  @Override
  public void invoke(DemoData demoData, AnalysisContext analysisContext) {
    log.info("解析到一条数据:{}", JSON.toJSONString(demoData));
    list.add(demoData);

  }


  /**
   * 所有数据解析完了，都会调用
   * @param analysisContext
   */
  @Override
  public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    log.info("所有数据解析完成!");
  }
}
