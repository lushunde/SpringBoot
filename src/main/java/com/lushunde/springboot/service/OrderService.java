package com.lushunde.springboot.service;

/**
 * @author qingshan
 */
public interface OrderService {
    void queryOrderById(Long orderId);

    void insertBatchOrder();
}
