package com.eh.cloud.auth.model.entity.common;

import lombok.Data;

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
    private String url;

    public ErrorResponse(int code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public ErrorResponse(int code, String msg, String url) {
        this.code=code;
        this.msg=msg;
        this.url = url;
    }

    public ErrorResponse(int code, String msg, String trace, String url) {
        this.code=code;
        this.msg=msg;
        this.trace = trace;
        this.url = url;
    }
}
