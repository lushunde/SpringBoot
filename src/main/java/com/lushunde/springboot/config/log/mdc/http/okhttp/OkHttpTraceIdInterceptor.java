package com.lushunde.springboot.config.log.mdc.http.okhttp;

import com.lushunde.springboot.config.constant.Constants;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import org.slf4j.MDC;

import java.io.IOException;

/**
 * @ClassName OkHttpTraceIdInterceptor
 * @Description OkHttp 拦截设置 trderId
 *              private static OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(new OkHttpTraceIdInterceptor()).build();
 * @Author bellus
 * @Date 2022/6/9 11:43
 * @Version 1.0.0
 **/

public class OkHttpTraceIdInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        String traceId = MDC.get(Constants.TRACE_ID);
        Request request = null;
        if (traceId != null) {
            //添加请求体
            request = chain.request().newBuilder().addHeader(Constants.TRACE_ID, traceId).build();
        }
        Response originResponse = chain.proceed(request);

        return originResponse;
    }
}
