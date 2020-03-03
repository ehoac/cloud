package com.eh.cloud.auth.api.config.exception.biz;/**
 * Created by caopeihe on 2020/3/2.
 */

import com.eh.cloud.auth.model.constants.BaseErrors;
import com.eh.cloud.auth.api.config.exception.BizException;
import org.springframework.http.HttpStatus;

/**
 * author: caopeihe
 * date: 2020/3/2 17:14
 * desc: ServerSideException
 */
public class ServerSideException extends BizException {

    public <E extends Enum<E> & BaseErrors> ServerSideException(E exceptionCode, Throwable cause) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, exceptionCode, cause);
    }

    public <E extends Enum<E> & BaseErrors> ServerSideException(E exceptionCode) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, exceptionCode, null);
    }
}