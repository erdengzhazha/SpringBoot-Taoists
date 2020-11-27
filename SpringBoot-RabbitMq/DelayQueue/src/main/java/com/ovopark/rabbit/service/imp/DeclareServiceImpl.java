package com.ovopark.rabbit.service.imp;

import com.ovopark.rabbit.service.DeclareService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description 声明服务
 * @author  Xiu_Er 13813641925@163.com
 * @Date 2020年10月15号 下午7:56
 */
@Service
public class DeclareServiceImpl implements DeclareService {

  @Autowired
  private RabbitAdmin rabbitAdmin;
  @Override
  public String declareDelayPackage(String projectName) {
    //创建套件
    /**
     * 注入一个projectName交换机
     * @return
     */
    Exchange orderExchange = new DirectExchange("exchange."+projectName, true, false, null);
    rabbitAdmin.declareExchange(orderExchange);

    Map<String, Object> map = new HashMap<String, Object>(1);
    map.put("x-dead-letter-exchange", "dtl.exchange."+projectName);
    /**
     * 注入一个具有失效时间的queue
     * @return
     */
    Queue orderQueue = new Queue("ttl.queue."+projectName, true, false, false, map);
    rabbitAdmin.declareQueue(orderQueue);
    /**
     * 绑定exchange.workorder(交换机) 和 ttl.queue.workorder (队列)
     * @return
     */
    Binding bindingTTL = new Binding("ttl.queue."+projectName, Binding.DestinationType.QUEUE, "exchange."+projectName, "delay."+projectName, null);
    rabbitAdmin.declareBinding(bindingTTL);

    /**
     * 注入工单死信交换机
     * @return
     */
    Exchange dtlOrderExchange = new TopicExchange("dtl.exchange."+projectName, true, false, null);
    rabbitAdmin.declareExchange(dtlOrderExchange);

    /**
     * 注入一个工单死信队列
     * @return
     */
    Queue dtlOrderQueue = new Queue("dtl.queue." + projectName, true, false, false);
    rabbitAdmin.declareQueue(dtlOrderQueue);

    /**
     * 绑定 dtl.exchange.workorder(交换机) 和 dtl.queue.workorder (队列)
     * @return
     */
    Binding dtlOrderBinding = new Binding("dtl.queue."+projectName, Binding.DestinationType.QUEUE, "dtl.exchange."+projectName, "#", null);
    rabbitAdmin.declareBinding(dtlOrderBinding);

    return "延迟队列套件声明成功!";
  }
}
