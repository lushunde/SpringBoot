package com.lushunde.springboot.RabbitMQ.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName TopicConfig
 * @Description Topics模式  交换机类型 topic
 * @Author bellus
 * @Date 2022/5/30 18:47
 * @Version 1.0.0
 **/


@Configuration
public class TopicConfig {


    @Bean
    public Queue topicQ1() {
        return new Queue("topic_mq_shanghai");
    }
    @Bean
    public Queue topicQ2() {
        return new Queue("topic_mq_beijing");
    }



    @Bean
    public TopicExchange setTopicExchange() {
        return new TopicExchange("topicExchange");
    }


    @Bean
    public Binding bindTopicHebei1() {
        return BindingBuilder.bind(topicQ1()).to(setTopicExchange()).with("changsha.*");
    }
    @Bean
    public Binding bindTopicHebei2() {
        return BindingBuilder.bind(topicQ2()).to(setTopicExchange()).with("#.beijing");
    }

}