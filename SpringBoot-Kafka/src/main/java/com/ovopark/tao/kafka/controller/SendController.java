package com.ovopark.tao.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.util.Date;

/*** * http://www.qiusunzuo.com/
 *                     .::::. *       FUCK YOU     
 *                  .::::::::.  @Description 请填写类注释
 *                 :::::::::::  @Auther Xiu_Er 13813641925@163.com
 *             ..:::::::::::'   @Date 2021/1/5 9:03 下午
 *           '::::::::::::'     @Version 1.0.0    
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 */
@Slf4j
@RestController
public class SendController {

  @Autowired
  private KafkaTemplate<Integer, String> template;

  @RequestMapping("/v1/send")
  public String send(String topicName){
    template.send(topicName, "{\"message\":\"雄"+new Date().getTime() +"起\",\"bussinesType\":\"push\",\"moduleKey\":\"device\",\"enterpriseId\":1084}");
    template.flush();
    return "发送成功!";
  }

  /** 定义一个计数器*/
  private int i=0;
  @RequestMapping("/v1/send02")
  public String send(String topicName,Integer partitionSize){
    synchronized (this) {
      if (i > partitionSize) {
        i = 0;
      }
      log.info("partitionSize : {} ,key : {}", i % (partitionSize - 1), i);
      template.send(topicName, i % (partitionSize - 1), i, "{\"message\":\"雄" + new Date().getTime() + "起\",\"bussinesType\":\"push\",\"moduleKey\":\"device\",\"enterpriseId\":1084}");
      template.flush();
      i++;
      return "发送成功!";
    }
  }
}
