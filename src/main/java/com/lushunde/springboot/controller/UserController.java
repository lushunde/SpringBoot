package com.lushunde.springboot.controller;

import com.lushunde.springboot.config.constant.ExceptionEnum;
import com.lushunde.springboot.config.exception.BusinException;
import com.lushunde.springboot.model.User;
import com.lushunde.springboot.config.result.R;
import com.lushunde.springboot.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description 用户信息
 * @Author bellus
 * @Date 2022/6/8 0:02
 * @Version 1.0.0
 **/

@RequestMapping(value = "/user/")
@RestController
@Api(tags = "用户表")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "queryUserByUserId")
    @ApiOperation(value = "根据主键获取用户信息")
    public R<User> queryUserByUserId(Integer userId) throws BusinException {
       try{
           User user = this.userService.queryUserByUserId(userId);
           return R.ok().data(user);
       }catch (Exception e) {
           throw BusinException.newInstance(ExceptionEnum.INTERNAL_SERVER_ERROR, e).log(log);

       }

    }


    @PostMapping(value = "queryException")
    @ApiOperation(value = "根据异常捕获")
    public R<User> queryException(Integer userId) throws BusinException {
        try{

            userId = 1/userId;

            return R.ok().data(userId);
        }catch (Exception e) {
            throw BusinException.newInstance(ExceptionEnum.INTERNAL_SERVER_ERROR, e).log(log);

        }

    }

}
