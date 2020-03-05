package com.eh.cloud.auth.api.config.exception;

import com.eh.cloud.auth.model.constants.BaseErrors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author caopeihe
 * @Type BizException.java
 * @Desc
 * @date 2020/2/27 17:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BizException extends RuntimeException{
    public static final String HTTP_STATUS = "status";
    public static final String ERROR_CODE = "code";
    public static final String ERROR_MESSAGE = "msg";
    public static final String ERROR_TRACE = "trace";
    public static final String ERROR_PATH = "path";

    private int status;
    private String code;
    private String msg;
    private String timestamp;
    private String uri;

    public BizException(int status, String code, String msg) {
        this.status = status;
        this.code = code;
        this.msg = msg;
    }

    public <E extends Enum<E> & BaseErrors> BizException(HttpStatus httpStatus, E code,
                                                         Throwable cause) {
        this.status = httpStatus.value();
        this.code = code.getCode();
        if (cause == null) {
            this.msg = code.getMsg();
        } else {
            this.msg = cause.getMessage();
        }
        this.timestamp = String.valueOf(LocalDateTime.now());
        this.uri = uri;
     }
}
