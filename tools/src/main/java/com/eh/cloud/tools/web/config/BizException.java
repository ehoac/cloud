package com.eh.cloud.tools.web.config;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author caopeihe
 * @Type BizException.java
 * @Desc
 * @date 2020/2/27 17:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizException extends Exception{
    public static final String HTTP_STATUS = "status";
    public static final String ERROR_CODE = "code";
    public static final String ERROR_MESSAGE = "msg";
    public static final String ERROR_TRACE = "trace";

    private String code;
    private String msg;
    private String timestamp;
    private String uri;

    public BizException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizException(String code, String msg, String uri) {
        this.code = code;
        this.msg = msg;
        this.timestamp = String.valueOf(LocalDateTime.now());
        this.uri = uri;
    }

    public String toJsonString(){
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        json.put("path", uri);
        json.put("timestamp", timestamp);
        return json.toString();
    }
}
