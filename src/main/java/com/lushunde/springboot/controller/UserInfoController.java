package com.lushunde.springboot.controller;

import com.lushunde.springboot.entity.UserInfo;
import com.lushunde.springboot.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserInfoController
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/6 22:27
 * @Version 1.0.0
 **/

@RestController
@RequestMapping(value = "/userinfo")
@Api(tags="用户Sharding-JDBC测试")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;


    @GetMapping(value = "insertUserInfoDemo")
    @ApiOperation(value="插入用户", notes="Sharding-jdbc 插入数据库，根据 user_id %2 选择库")
    public void insertUserInfoDemo() {
        // 插入10条用户数据
        userInfoService.insertBatchUserInfo();
    }

    @GetMapping(value = "getUserInfoDemo")
    @ApiOperation(value="查询用户", notes="Sharding-jdbc 查询数据库，根据 user_id %2 选择库")
    public UserInfo getUserInfoDemo(Long userId) {

        UserInfo user =  userInfoService.queryUserIfo(userId);
    return user;
    }



    @GetMapping(value = "testTransactional")
    @ApiOperation(value="插入用户XA事物测试", notes="Sharding-jdbc 插入数据库，根据 user_id %2 选择库，分布式事物")
    public void testTransactional() {

        this.userInfoService.testTransactional();

    }

}
