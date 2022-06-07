package com.lushunde.springboot.entity;

import lombok.Data;

/**
 * @author qingshan
 */
@Data
public class OrderItem {

    private Long orderId;

    private String userName;

    private String account;

    private String password;
}
