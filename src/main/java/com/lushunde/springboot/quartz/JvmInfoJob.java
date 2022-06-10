package com.lushunde.springboot.quartz;

import com.lushunde.springboot.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName MailJob
 * @Description 定时发送
 * @Author bellus
 * @Date 2022/6/9 14:06
 * @Version 1.0.0
 **/
@Slf4j
@DisallowConcurrentExecution
public class JvmInfoJob implements Job {

    @Autowired
    private UserService userService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //获取job参数
        JobDataMap jobdatamap = context.getJobDetail().getJobDataMap();
        // 获取
        //context.getScheduler().getContext().get()

        long l = Runtime.getRuntime().maxMemory();

        long freeMemory = Runtime.getRuntime().freeMemory();

        int availableProcessors = Runtime.getRuntime().availableProcessors();

        long totalMemory = Runtime.getRuntime().totalMemory();


        log.info("JVM参数 总核心数={},总内存={},剩余内存={}",availableProcessors,totalMemory,freeMemory);




    }


}
