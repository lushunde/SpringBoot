package com.lushunde.springboot.config.log.mdc.http.resttemplate;

import com.lushunde.springboot.config.constant.Constants;
import org.slf4j.MDC;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * @ClassName RestTemplateTraceIdInterceptor
 * @Description restTemplate 拦截设置 trderId
 *              restTemplate.setInterceptors(Arrays.asList(new RestTemplateTraceIdInterceptor()));
 * @Author bellus
 * @Date 2022/6/9 11:53
 * @Version 1.0.0
 **/

public class RestTemplateTraceIdInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        String traceId = MDC.get(Constants.TRACE_ID);
        if (traceId != null) {
            httpRequest.getHeaders().add(Constants.TRACE_ID, traceId);
        }

        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
