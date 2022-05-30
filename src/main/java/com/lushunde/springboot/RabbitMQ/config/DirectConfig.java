package com.lushunde.springboot.RabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName DirectConfig
 * @Description 路由模式|Routing模式   交换机类型：direct
 * @Author bellus
 * @Date 2022/5/30 18:46
 * @Version 1.0.0
 **/


@Configuration
public class DirectConfig {

    /**
     * @Description 声明队列
     * @Author Bellus
     * @Date 2022/5/30 18:49
     * @Version 1.0.0
     * @Param []
     * @return org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue directQueueShanghai() {
        return new Queue("direct_mq_shanghai");
    }
    @Bean
    public Queue directQueueBeijing() {
        return new Queue("direct_mq_beijing");
    }


    /**
     * @Description 声明exchange
     * @Author Bellus
     * @Date 2022/5/30 18:50
     * @Version 1.0.0
     * @Param []
     * @return org.springframework.amqp.core.DirectExchange
     **/
    @Bean
    public DirectExchange setDirectExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * @Description 声明binding，需要声明一个routingKey
     * @Author Bellus
     * @Date 2022/5/30 18:50
     * @Version 1.0.0
     * @Param []
     * @return org.springframework.amqp.core.Binding
     **/
    @Bean
    public Binding bindDirectBind1() {
        return BindingBuilder.bind(directQueueShanghai()).to(setDirectExchange()).with("china.shanghai");
    }
    @Bean
    public Binding bindDirectBind2() {
        return BindingBuilder.bind(directQueueBeijing()).to(setDirectExchange()).with("china.beijing");
    }

}