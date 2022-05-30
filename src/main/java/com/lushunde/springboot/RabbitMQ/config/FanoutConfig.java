package com.lushunde.springboot.RabbitMQ.config;

/**
 * @ClassName FanoutConfig
 * @Description Fanout模式需要声明exchange，并绑定queue，由exchange负责转发到queue上。  广播模式 交换机类型设置为：fanout
 * @Author bellus
 * @Date 2022/5/30 18:46
 * @Version 1.0.0
 **/

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutConfig {


    @Bean
    public Queue fanoutQueueShanghai() {
        return new Queue("fanout.mq.shanghai");
    }
    @Bean
    public Queue fanoutQueueBeijing() {
        return new Queue("fanout.mq.beijing");
    }



    @Bean
    public FanoutExchange setFanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }



    @Bean
    public Binding bindQ1() {
        return BindingBuilder.bind(fanoutQueueShanghai()).to(setFanoutExchange());
    }
    @Bean
    public Binding bindQ2() {
        return BindingBuilder.bind(fanoutQueueBeijing()).to(setFanoutExchange());
    }
}
