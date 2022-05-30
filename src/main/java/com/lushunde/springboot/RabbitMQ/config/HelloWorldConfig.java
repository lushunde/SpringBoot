package com.lushunde.springboot.RabbitMQ.config;

/**
 * @ClassName HelloWorldConfig
 * @Description 直连模式只需要声明队列，所有消息都通过队列转发。无需设置交换机（实际使用的Exchange: (AMQP default)默认交换机）
 * @Author bellus
 * @Date 2022/5/30 18:35
 * @Version 1.0.0
 **/

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class HelloWorldConfig {

    @Bean
    public Queue setQueue() {
        return new Queue("helloWorldqueue");
    }
}