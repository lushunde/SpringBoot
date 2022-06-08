package com.lushunde.springboot.service;

import com.lushunde.springboot.model.User;

/**
 * @ClassName UserService
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/8 15:10
 * @Version 1.0.0
 **/

public interface UserService {
    User queryUserByUserId(Integer userId) throws Exception;


}
