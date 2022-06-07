package com.lushunde.springboot.service.impl;


import com.lushunde.springboot.entity.Order;
import com.lushunde.springboot.mapper.OrderMapper;
import com.lushunde.springboot.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qingshan
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    public static Long orderId = 1L;

    @Override
    public void queryOrderById(Long orderId) {
        log.info(">>>>>>>>>queryOrderById>>>>>>>>>");
        Order order = orderMapper.selectByPrimaryKey(orderId);
        log.info(">>>>>>>>>order:[{}]",order);
    }

    @Override
    public void insertBatchOrder() {
        System.out.println(">>>>>>>>>>>>insertBatchOrderInfo>>>>>>>>>>>>");
        for (long i = 1; i <= 10; i++) {
            Order order = new Order();
            order.setOrderId(i+1);
            order.setUserId(i);

            orderMapper.insert(order);
        }
        System.out.println(">>>>>>>>>>>>over>>>>>>>>>>>>");
    }
}
