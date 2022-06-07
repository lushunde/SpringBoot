package com.lushunde.springboot.service.impl;

import com.lushunde.springboot.entity.UserInfo;
import com.lushunde.springboot.mapper.UserInfoMapper;
import com.lushunde.springboot.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qingshan
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    public static Long userId = 1L;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBatchUserInfo() {
        log.info("insertBatchUserInfo--------------");
        for (int i = 1; i <= 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setAccount("Account" + i);
            userInfo.setPassword("pass" + i);
            userInfo.setUserName("name" + i);
            userId++;
            userInfoMapper.insert(userInfo);
        }
        log.info("over..........");
    }

    @Override
    public UserInfo queryUserIfo(Long uid) {
        UserInfo user = userInfoMapper.selectByPrimaryKey(uid);
        log.info("user:[{}]",user);
        return  user;
    }

    /**
     * 跨库事务 XA
     */
    @Override
    @ShardingTransactionType(TransactionType.XA)
    @Transactional(rollbackFor = Exception.class)
    public void testTransactional() {
        testTransactional1();
        testTransactional2();
        testTransactional2();

    }


    public void testTransactional1() {
        // 取模 121:3306/ds0
        UserInfo user1 = new UserInfo();
        user1.setUserId(2674L);
        user1.setAccount("62260975");
        user1.setPassword("root");
        user1.setUserName("root");
        this.userInfoMapper.insert(user1);


    }

    public void testTransactional2() {

        // 取模 121:3306/ds1
        UserInfo user2 = new UserInfo();
        user2.setUserId(2673L);
        user2.setAccount("400800900");
        user2.setUserName("admin");
        user2.setPassword("admin");
        // 主键冲突
        this.userInfoMapper.insert(user2);
    }


}
