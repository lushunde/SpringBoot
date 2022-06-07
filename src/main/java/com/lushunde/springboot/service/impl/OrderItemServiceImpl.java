package com.lushunde.springboot.service.impl;

import com.lushunde.springboot.entity.OrderItem;
import com.lushunde.springboot.mapper.OrderItemMapper;
import com.lushunde.springboot.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author qingshan
 */
@Slf4j
@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Resource
    private OrderItemMapper orderItemMapper;

    public static Long orderId = 1L;

    @Override
    public void queryOrderItemById(Long orderId) {
        log.info(">>>>>>>>>queryOrderById>>>>>>>>>");
        OrderItem order = orderItemMapper.selectByPrimaryKey(orderId);
        log.info(">>>>>>>>>order:[{}]",order);
    }

    @Override
    public void insertBatchOrderItem() {
        System.out.println(">>>>>>>>>>>>insertBatchOrderInfo>>>>>>>>>>>>");
        for (int i = 1; i <= 10; i++) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setAccount("Account-w" + i);
            orderItem.setPassword("pass-w" + i);
            orderItem.setUserName("name-w" + i);
            orderId++;
            orderItemMapper.insert(orderItem);
        }
        System.out.println(">>>>>>>>>>>>over>>>>>>>>>>>>");
    }
}
