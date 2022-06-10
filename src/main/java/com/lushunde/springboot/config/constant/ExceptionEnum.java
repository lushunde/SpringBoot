package com.lushunde.springboot.config.constant;

/**
 * @ClassName ExceptionEnum
 * @Description 异常错误码
 * @Author bellus
 * @Date 2022/6/8 19:13
 * @Version 1.0.0
 **/

public enum ExceptionEnum {


    BAD_REQUEST(400, "请求数据格式不正确!"),
    UNAUTHORIZED(401, "登录凭证过期!"),
    FORBIDDEN(403, "没有访问权限!"),
    NOT_FOUND(404, "请求的资源找不到!"),

    INTERNAL_SERVER_ERROR(500, "服务器内部错误!"),


    QUARTZ_INIT_ERROR(500100, "初始化定时调度管理类出错"),
    QUARTZ_DESTROY_ERROR(500101, "关闭定时调度管理类出错"),
    QUARTZ_GET_SCHEDULER_ERROR(500102, "获取scheduler出错"),
    QUARTZ_TRIGGER_FOUNT_ERROR(500102, "trigger已经存在，不能添加"),

    SERVICE_UNAVAILABLE(503, "服务器正忙,请稍后再试!"),
    UNKNOWN(10000, "未知异常!");


    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误描述
     */
    private String msg;

    ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
