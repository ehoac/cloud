package com.eh.cloud.auth.model.constants;

import java.time.LocalDateTime;

/**
 * @author caopeihe
 * @Type ExceptionCode.java
 * @Desc
 * @date 2020/2/27 17:01
 */
public enum ExceptionEnum implements BaseErrors {
    /**
     * Exception Code
     */
    INTER_ERROR("系统异常"),
    USER_LOGIN_ERROR("用户名或者密码错误 请重新登录"),
    USER_LOGIN_EXPIRED("用户未登录或登录超时，请重新登录"),
    USER_NO_ACCESS("无访问权限"),
    ILLEGAL_ARGUMENT("参数错误"),
    NO_TOKEN("参数无TOKEN"),
    TOKEN_EXPIRED("TOKEN 已过期"),
    TOKEN_INVALID("无效的TOKEN");

    private int status;
    private String code;
    private String msg;
    private String timestamp;

    private ExceptionEnum(String msg) {
        this.msg = msg;
    }

    private ExceptionEnum(int status, String msg){
        this.status = status;
        this.msg = msg;
    }



    @Override
    public int status() {
        return status;
    }


    @Override
    public String getCode() {
        return name();
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public String timestamp() {
        return String.valueOf(LocalDateTime.now());
    }
}
