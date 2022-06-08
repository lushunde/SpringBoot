package com.lushunde.springboot.service.impl;

import com.lushunde.springboot.mapper.UserMapper;
import com.lushunde.springboot.model.User;
import com.lushunde.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImpl
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/8 15:11
 * @Version 1.0.0
 **/

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryUserByUserId(Integer userId) throws Exception {

        return userMapper.selectByPrimaryKey(userId);
    }
}
