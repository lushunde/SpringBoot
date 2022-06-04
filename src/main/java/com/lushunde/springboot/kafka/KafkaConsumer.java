package com.lushunde.springboot.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @ClassName KafkaConsumer
 * @Description kafka 消费者
 * @Author bellus
 * @Date 2022/6/2 8:28
 * @Version 1.0.0
 **/
@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = KafkaInfo.TOPIC_TEST, groupId = KafkaInfo.TOPIC_GROUP1)
    public void topicTest(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {

        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("topic_test 消费了：Topic:" + topic + ",Message:" + msg);
            ack.acknowledge();
        }
    }
}
