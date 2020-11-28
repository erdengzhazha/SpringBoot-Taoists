package com.ovopark.delayplugin.service;

import com.ovopark.delayplugin.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 消息监听方法
 * @author Xiu_Er 13813641925@163.com
 * @Date 2020年10月15号 下午 8:59
 */
@Service
public class MessageService {

  Logger log = LoggerFactory.getLogger(MessageService.class);



  /**
   * 处理技术客服的结单请求
   * @param order
   */
  public void handleOrderMessage(Order order) {

    System.out.println(new SimpleDateFormat("yyyyMMdd hh:mm:ss").format(new Date())+"接收到了延迟的消息:"+order.toString());

  }
}
