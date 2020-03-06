package com.eh.cloud.auth.model.entity.common;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author caopeihe
 * @Type ErrorResponseEntity.java
 * @Desc
 * @date 2020/2/27 16:53
 */
@Data
public class ErrorResponse {
    private int code;
    private String msg;
    private String trace;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public ErrorResponse(int code, String msg, String path) {
        this.code=code;
        this.msg=msg;
        this.path = path;
    }

    public ErrorResponse(int code, String msg, String trace, String path) {
        this.code=code;
        this.msg=msg;
        this.trace = trace;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}
