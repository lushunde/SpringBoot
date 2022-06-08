package com.lushunde.springboot.config.exception;

import com.lushunde.springboot.config.constant.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

/**
 * @ClassName BusinException
 * @Description 自定义异常类
 * @Author bellus
 * @Date 2022/6/8 19:00
 * @Version 1.0.0
 **/

@Slf4j
public class BusinException extends Exception{

    private Exception exception;
    private Integer code;
    private String msg;

    private BusinException() {
        super();
    }

    public static BusinException newInstance(ExceptionEnum exceptionEnum){
        BusinException be = new BusinException();
        be.setCode(exceptionEnum.getCode());
        be.setMsg(exceptionEnum.getMsg());
        return be;
    }


    public static BusinException newInstance(ExceptionEnum exceptionEnum,Exception e) {
        BusinException be = new BusinException();
        be.setException(e);
        be.setCode(exceptionEnum.getCode());
        be.setMsg(exceptionEnum.getMsg());
        return be;
    }


    public BusinException log() {
        log.error("创建BusinException异常时打印log：Code={},msg={},exception={}",this.getCode(),this.getMsg(),this.getException());
        return this;
    }

    public BusinException log(Logger logger) {
       logger.error("创建BusinException异常时打印log：Code={},msg={},exception={}",this.getCode(),this.getMsg(),this.getException());
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    private void setException(Exception exception) {
        this.exception = exception;
    }

    private void setCode(Integer code) {
        this.code = code;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }


}
