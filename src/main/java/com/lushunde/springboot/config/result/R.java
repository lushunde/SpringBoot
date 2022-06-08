package com.lushunde.springboot.config.result;


import com.lushunde.springboot.config.constant.ResultCode;
import com.lushunde.springboot.config.exception.BusinException;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * @ClassName R
 * @Description 返回统一样式定义
 *          如果result=1，则可以直接解析 data 数据（code和message 不重要）
 *          如果result=2，则可以直接解析 code 和展示message信息  （data字段不重要）
 * @Author bellus
 * @Date 2022/6/8 16:42
 * @Version 1.0.0
 **/

public class R<T> implements Serializable {

    // 1-成功   2-异常
    private Integer result;

    private Integer code;

    private String message;

    private T data ;



    // 构造器私有
    private R(){}



    // 通用返回成功
    public static R ok() {
        R r = new R();
        r.setResult(1);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(ResultCode.SUCCESS.getMessage());
        return r;
    }

    // 通用返回成功
    public static R ok(String message) {
        R r = new R();
        r.setResult(1);
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setMessage(StringUtils.hasText(message)? message:ResultCode.SUCCESS.getMessage());
        return r;
    }


    // 通用返回失败，未知错误
    public static R error() {
        R r = new R();
        r.setResult(2);
        r.setCode(ResultCode.ERROR_UNKNOWN.getCode());
        r.setMessage(ResultCode.ERROR_UNKNOWN.getMessage());
        return r;
    }


    public static R error (Exception e) {
        R r = new R();
        r.setResult(2);

        if(e instanceof BusinException) {
            BusinException be = (BusinException) e;
            r.setCode(be.getCode());
            r.setMessage(be.getMsg());
        }else if(e instanceof NullPointerException){
            r.setCode(ResultCode.ERROR_INTERNAL_SERVER.getCode());
            r.setMessage(ResultCode.ERROR_INTERNAL_SERVER.getMessage());
        }else{
            r.setCode(ResultCode.ERROR_UNKNOWN.getCode());
            r.setMessage(ResultCode.ERROR_UNKNOWN.getMessage());
        }
        return r;
    }

    /**------------使用链式编程，返回类本身-----------**/

    // 自定义返回数据
    public R data(T data) {
        this.setData(data);
        return this;
    }

    private void setResult(Integer result) {
        this.result = result;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setData( T data) {
        this.data = data;
    }


    public Integer getResult() {
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
