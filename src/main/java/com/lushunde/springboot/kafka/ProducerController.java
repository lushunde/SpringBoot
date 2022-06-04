package com.lushunde.springboot.kafka;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ProducerController
 * @Description 前段调用 生产者 消息
 * @Author bellus
 * @Date 2022/6/2 8:32
 * @Version 1.0.0
 **/

@RestController
@RequestMapping(value = "kafka")
@Api(tags = "kafka演示")
public class ProducerController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/mkdirMessage")
    @ApiOperation(value = "调用制造消息")
    public String mkdirMessage(String message){

        if(StringUtils.isEmpty(message)){
            System.out.println("生产者：传入内容为空,直接忽略");
            return "空";
        }else{
            kafkaProducer.send(message);
            System.out.println("生产者：传入内容为"+message+",已发送");
            return "message";
        }

    }

}
