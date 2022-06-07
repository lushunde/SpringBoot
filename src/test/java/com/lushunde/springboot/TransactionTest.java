package com.lushunde.springboot;


import com.lushunde.springboot.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: qingshan
 * @Description: 咕泡学院，只为更好的你
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TransactionTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void transTest() {
        // 主键冲突异常
        this.userInfoService.testTransactional();
    }

}
