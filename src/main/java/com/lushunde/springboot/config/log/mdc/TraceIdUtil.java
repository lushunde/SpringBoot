package com.lushunde.springboot.config.log.mdc;

import java.util.UUID;

/**
 * @ClassName TraceIdUtil
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/9 10:20
 * @Version 1.0.0
 **/

public class TraceIdUtil {

    public static String getTraceId(){
        return UUID.randomUUID().toString();
    }


}
