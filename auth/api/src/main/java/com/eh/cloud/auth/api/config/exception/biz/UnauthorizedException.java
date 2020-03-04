package com.eh.cloud.auth.api.config.exception.biz;

import com.eh.cloud.auth.model.constants.BaseErrors;
import com.eh.cloud.auth.api.config.exception.BizException;
import org.springframework.http.HttpStatus;

/**
 * author: caopeihe
 * date: 2020/3/2 17:15
 * desc: UnauthorizedException
 */
public class UnauthorizedException extends BizException {

    public <E extends Enum<E> & BaseErrors> UnauthorizedException(E exceptionCode, Throwable cause) {
        super(HttpStatus.UNAUTHORIZED, exceptionCode, cause);
    }

    public <E extends Enum<E> & BaseErrors> UnauthorizedException(E exceptionCode) {
        super(HttpStatus.UNAUTHORIZED, exceptionCode, null);
    }
}