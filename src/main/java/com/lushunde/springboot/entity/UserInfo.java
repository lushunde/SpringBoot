package com.lushunde.springboot.entity;

import lombok.Data;

/**
 * @author qingshan
 */
@Data
public class  UserInfo {

    private Long userId;

    private String userName;

    private String account;

    private String password;
}
