package com.lushunde.springboot.service;

/**
 * @author qingshan
 */
public interface OrderItemService {
    void queryOrderItemById(Long orderId);

    void insertBatchOrderItem();
}
