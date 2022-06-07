package com.lushunde.springboot.service;

import com.lushunde.springboot.entity.UserInfo;

/**
 * @author qingshan
 */
public interface UserInfoService {

    void insertBatchUserInfo();

    UserInfo queryUserIfo(Long uid);

    void testTransactional();
}
