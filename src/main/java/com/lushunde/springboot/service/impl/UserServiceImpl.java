package com.lushunde.springboot.service.impl;

import com.lushunde.springboot.config.log.mdc.pool.ThreadPoolExecutorMdcWrapper;
import com.lushunde.springboot.mapper.UserMapper;
import com.lushunde.springboot.model.User;
import com.lushunde.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @ClassName UserServiceImpl
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/8 15:11
 * @Version 1.0.0
 **/

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    private static  final ThreadPoolExecutorMdcWrapper pool = new ThreadPoolExecutorMdcWrapper(10,15,60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    @Override
    public User queryUserByUserId(Integer userId) throws Exception {
    log.info("11111 userid={}",userId);

        pool.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println( userId);

                log.info("22222 userid={}",userId);
            }
        });


        Future<User> submit = pool.submit((Callable) () -> {
            System.out.println(userId);

            log.info("33333 userid={}", userId);
            return userMapper.selectByPrimaryKey(userId);
        });
        log.info("44444 user={}",submit.get());


        return userMapper.selectByPrimaryKey(userId);
    }
}
