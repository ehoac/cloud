package com.eh.cloud.auth.api.config.exception.biz;/**
 * Created by caopeihe on 2020/3/2.
 */

import com.eh.cloud.auth.model.constants.BaseErrors;
import com.eh.cloud.auth.api.config.exception.BizException;
import org.springframework.http.HttpStatus;

/**
 * author: caopeihe
 * date: 2020/3/2 17:16
 * desc: ForbiddenException
 */
public class ForbiddenException extends BizException {
    public <E extends Enum<E> & BaseErrors> ForbiddenException(E exceptionCode, Throwable cause) {
        super(HttpStatus.FORBIDDEN, exceptionCode, cause);
    }

    public <E extends Enum<E> & BaseErrors> ForbiddenException(E exceptionCode) {
        super(HttpStatus.FORBIDDEN, exceptionCode, null);
    }
}