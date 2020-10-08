package com.ovopark.tao.rabbitmq.orderserver.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ovopark.tao.rabbitmq.orderserver.entity.dto.OrderMessageDTO;
import com.ovopark.tao.rabbitmq.orderserver.entity.enummeration.OrderStatusEnum;
import com.ovopark.tao.rabbitmq.orderserver.entity.po.OrderDetailPO;
import com.ovopark.tao.rabbitmq.orderserver.entity.vo.OrderCreateVO;
import com.ovopark.tao.rabbitmq.orderserver.mapper.OrderDetailMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * 做业务请求的Service
 */
@Slf4j
@Service
public class OrderService {

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 创建订单
     * @param orderCreateVO
     */
    public void createOrder(OrderCreateVO orderCreateVO) throws IOException, TimeoutException {
        OrderDetailPO orderDetailPO = new OrderDetailPO();
        orderDetailPO.setAddress(orderCreateVO.getAddress());
        orderDetailPO.setAccountId(orderCreateVO.getAccountId());
        orderDetailPO.setProductId(orderCreateVO.getProductId());
        orderDetailPO.setStatus(OrderStatusEnum.ORDER_CREATING);
        orderDetailPO.setDate(new Date());
        int insert = orderDetailMapper.insert(orderDetailPO);
        if(insert>0){
            //插入成功 , 给餐厅微服务发送消息
            OrderMessageDTO orderMessageDTO = new OrderMessageDTO();
            orderMessageDTO.setOrderId(orderDetailPO.getId());
            orderMessageDTO.setProductId(orderDetailPO.getProductId());
            orderMessageDTO.setAccountId(orderDetailPO.getAccountId());
            //发送消息
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("49.233.5.107");
            try(Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel();){
                //用jackson，将Object转json
                String messageToSend = objectMapper.writeValueAsString(orderMessageDTO);
                channel.basicPublish("exchange.order.restaurant", //exchange
                        "key.restaurant", //routing key
                        null, //无特殊要求
                        messageToSend.getBytes()//消息体本身
                         );
            }
        }else{
            //插入失败
            log.error("插入失败了! ");
        }
    }
}
