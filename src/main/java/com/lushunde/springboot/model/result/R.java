package com.lushunde.springboot.model.result;


import com.lushunde.springboot.constant.ResultCode;

import java.io.Serializable;

/**
 * @ClassName R
 * @Description 返回统一样式定义   如果result=1，则可以直接解析 data 数据（code和）
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

    // 通用返回失败，未知错误
    public static R error() {
        R r = new R();
        r.setResult(2);
        r.setCode(ResultCode.ERROR_UNKNOWN.getCode());
        r.setMessage(ResultCode.ERROR_UNKNOWN.getMessage());
        return r;
    }


    public R ResultCode(ResultCode result) {

        this.setCode(result.getCode());
        this.setMessage(result.getMessage());
        return this;
    }

    /**------------使用链式编程，返回类本身-----------**/

    // 自定义返回数据
    public R data(T data) {
        this.setData(data);
        return this;
    }



    // 自定义状态信息
    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    // 自定义状态码
    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    // 自定义返回结果
    public R result(Integer result) {
        this.setResult(result);
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
