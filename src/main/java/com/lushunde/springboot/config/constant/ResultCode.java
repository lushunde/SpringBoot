package com.lushunde.springboot.config.constant;

/**
 * @ClassName ResultCode
 * @Description 返回结果 枚举类
 * @Author bellus
 * @Date 2022/6/8 16:56
 * @Version 1.0.0
 **/


public enum ResultCode {
    SUCCESS(200,"成功"),
    ERROR_UNKNOWN(600,"未知错误"),
    ERROR_INTERNAL_SERVER(500, "服务器内部错误!"),
    ERROR_PARAM(400,"参数错误"),
    ERROR_AUTH(401,"身份认证失败")
    ;


    // 响应状态码
    private Integer code;
    // 响应信息
    private String message;

    ResultCode( Integer code, String message) {

        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }




}
