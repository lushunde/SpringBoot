package com.lushunde.springboot.config.log.mdc;

import com.lushunde.springboot.config.constant.Constants;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LogInterceptor
 * @Description MDC 存在的问题
 *                  子线程中打印日志丢失traceId  解决 ThreadPoolExecutorMdcWrapper
 *                  HTTP调用丢失traceId
 * 丢失traceId的情况，来一个再解决一个，绝不提前优化。
 * @Author bellus
 * @Date 2022/6/9 10:10
 * @Version 1.0.0
 **/

public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果有上层调用就用上层的ID
        String traceId = request.getHeader(Constants.TRACE_ID);
        if (traceId == null) {
            traceId = TraceIdUtil.getTraceId();
        }

        MDC.put(Constants.TRACE_ID, traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //调用结束后删除
        MDC.remove(Constants.TRACE_ID);
    }
}