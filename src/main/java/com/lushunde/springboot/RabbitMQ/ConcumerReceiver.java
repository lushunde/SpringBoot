package com.lushunde.springboot.RabbitMQ;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName ConcumerReceiver
 * @Description 消费者
 * @Author bellus
 * @Date 2022/5/30 18:33
 * @Version 1.0.0
 **/

@Component
public class ConcumerReceiver {


    //直连模式的多个消费者，会分到其中一个消费者进行消费。类似task模式
    //通过注入RabbitContainerFactory对象，来设置一些属性，相当于task里的channel.basicQos
    @RabbitListener(queues = "helloWorldqueue")
    public void helloWorldReceive(String message) {

        System.out.println("helloWorld模式 helloWorldqueue received message : " + message);
    }

    //工作队列模式
    @RabbitListener(queues = "work_mq")
    public void wordQueueReceiveq1(String message) {

        System.out.println("工作队列模式1 work_mq received message : " + message);
    }

    @RabbitListener(queues = "work_mq")
    public void wordQueueReceiveq2(String message) {

        System.out.println("工作队列模式2 work_mq received message : " + message);
    }


    //pub/sub模式进行消息监听
    @RabbitListener(queues = "fanout.mq.shanghai")
    public void fanoutReceiveq1(String message) {

        System.out.println("发布订阅模式1  fanout.mq.shanghai  received message : " + message);
    }

    @RabbitListener(queues = "fanout.mq.beijing")
    public void fanoutReceiveq2(String message) {

        System.out.println("发布订阅模式2  fanout.mq.beijing  received message : " + message);
    }


    //Routing路由模式
    @RabbitListener(queues = "direct_mq_shanghai")
    public void routingReceiveq1(String message) {

        System.out.println("Routing路由模式direct_mq_shanghai received message : " + message);
    }

    @RabbitListener(queues = "direct_mq_beijing")
    public void routingReceiveq2(String message) {

        System.out.println("Routing路由模式 direct_mq_beijing received message : " + message);
    }


    //topic 模式
    //注意这个模式会有优先匹配原则。例如发送routingKey=hunan.IT,那匹配到hunan.*(hunan.IT,hunan.eco),之后就不会再去匹配*.ITd
    @RabbitListener(queues = "topic_mq_shanghai")
    public void topicReceiveq1(String message) {
        System.out.println("Topic模式 topic_mq_shanghai received message : " + message);
    }

    @RabbitListener(queues = "topic_mq_beijing")
    public void topicReceiveq2(String message) {
        System.out.println("Topic模式 topic_mq_beijing received  message : " + message);
    }

}