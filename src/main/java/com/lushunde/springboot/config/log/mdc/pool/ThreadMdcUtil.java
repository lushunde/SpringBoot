package com.lushunde.springboot.config.log.mdc.pool;

import com.lushunde.springboot.config.constant.Constants;
import com.lushunde.springboot.config.log.mdc.TraceIdUtil;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @ClassName ThreadMdcUtil
 * @Description TODO 快来记录点什么吧，不要太懒了
 * @Author bellus
 * @Date 2022/6/9 10:14
 * @Version 1.0.0
 **/

public class ThreadMdcUtil {
    public static void setTraceIdIfAbsent() {
        if (MDC.get(Constants.TRACE_ID) == null) {
            MDC.put(Constants.TRACE_ID, TraceIdUtil.getTraceId());
        }
    }

    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                return callable.call();
            } finally {
                MDC.clear();
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            setTraceIdIfAbsent();
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}