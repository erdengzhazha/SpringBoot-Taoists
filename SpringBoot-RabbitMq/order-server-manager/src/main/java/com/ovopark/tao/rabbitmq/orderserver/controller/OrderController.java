package com.ovopark.tao.rabbitmq.orderserver.controller;

import com.ovopark.tao.rabbitmq.orderserver.entity.vo.OrderCreateVO;
import com.ovopark.tao.rabbitmq.orderserver.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("create")
    public void createOrder(OrderCreateVO orderCreateVO) throws IOException, TimeoutException {
        log.info("createOrder: orderCreateVo:{}"+orderCreateVO);
        orderService.createOrder(orderCreateVO);

    }
}
