package com.lushunde.springboot.controller;

import com.lushunde.springboot.service.OrderItemService;
import com.lushunde.springboot.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName OrderController
 * @Description 当单表    shard0.order0、shard0.order1、shard1.order0、shard1.order1
 * @Author bellus
 * @Date 2022/6/7 15:09
 * @Version 1.0.0
 **/
@RestController
@RequestMapping(value = "/order")
@Api(tags="订单-详情Sharding-JDBC测试")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderItemService orderItemService;


    @GetMapping(value = "insertOrderDemo")
    @ApiOperation(value="插入订单", notes="Sharding-jdbc 插入数据库，根据 order_id %2 选择库，根据order_id选择 表")
    public void insertOrderDemo() {
        // 插入10条用户数据
        orderService.insertBatchOrder();
    }


    @GetMapping(value = "insertOrderItemDemo")
    @ApiOperation(value="插入订单item", notes="Sharding-jdbc 插入数据库，根据 user_id %2 选择库")
    public void insertOrderItemDemo() {
        // 插入10条用户数据
        orderItemService.insertBatchOrderItem();
    }


}
