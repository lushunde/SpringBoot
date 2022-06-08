package com.lushunde.springboot.config.exception;

import com.lushunde.springboot.config.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Description 统一异常处理
 * @Author bellus
 * @Date 2022/6/8 20:38
 * @Version 1.0.0
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常处理
     *
     * @param e 业务异常
     * @return
     */
    @ExceptionHandler(value = BusinException.class)
    @ResponseBody
    public R exceptionHandler(BusinException e) {
        e.log(log);
        return R.error(e);
    }

    /**
     * 未知异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception e) {
        // 把错误信息输入到日志中
        log.error("统一异常拦截时打印log={}",e);

        return R.error();
    }



    /**
     * 空指针异常
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public R exceptionHandler(NullPointerException e) {
        log.error("统一异常拦截时打印log={}",e);
        return R.error(e);
    }

}
