package com.lushunde.springboot;


import com.lushunde.springboot.service.OrderItemService;
import com.lushunde.springboot.service.OrderService;
import com.lushunde.springboot.service.UserInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardTableTest {

    @Autowired
    private UserInfoService userInfoService;

    @Resource
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @Test
    public void insertUserInfoDemo() {
        // 插入10条用户数据
        userInfoService.insertBatchUserInfo();
    }

    @Test
    public void queryUserInfo() {
        // 读写分离，发送到 slave1 查询 user_info
        //userInfoService.queryUserIfo(1L);
        // 读写分离，发送到 slave0 查询 user_info
         userInfoService.queryUserIfo(2L);
    }


    @Test
    public void insertOrder() {
        // 插入10条 order数据
        orderService.insertBatchOrder();
    }

    @Test
    public void queryOrderById() {
        // 读写分离，发送到 slave1 查询 t_order
        // orderService.queryOrderById(1L);
        // 读写分离，发送到 slave0 查询 t_order
        orderService.queryOrderById(2L);
    }


    @Test
    public void insertOrderItem() {
        // 插入10条 orderItem数据
        orderItemService.insertBatchOrderItem();
    }

    @Test
    public void queryOrderItemById() {
        // 读写分离，发送到 slave1 查询 t_order_item
        // orderInfoService.queryOrderById(1L);
        // 读写分离，发送到 slave0 查询 t_order_item
        orderItemService.queryOrderItemById(2L);
    }

}
