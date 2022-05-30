package com.lushunde.springboot.RabbitMQ.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName WorkConfig
 * @Description 直连工作模式
 * @Author bellus
 * @Date 2022/5/30 18:45
 * @Version 1.0.0
 **/

@Configuration
public class WorkConfig {


    @Bean
    public Queue workQ1() {
        return new Queue("work_mq");
    }
}