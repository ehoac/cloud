package com.eh.cloud.auth.api.util;

import com.eh.cloud.auth.api.config.exception.BizException;
import com.eh.cloud.auth.api.config.exception.biz.ClientSideException;
import com.eh.cloud.auth.api.config.exception.biz.ForbiddenException;
import com.eh.cloud.auth.api.config.exception.biz.ServerSideException;
import com.eh.cloud.auth.api.config.exception.biz.UnauthorizedException;
import com.eh.cloud.auth.model.constants.ExceptionEnum;

/**
 * @author caopeihe
 * @Type ExceptionUtil.java
 * @Desc
 * @date 2020/2/28 9:05
 */
public class ExceptionUtil {

    public static String extractStackTrace (Throwable error){
        return error.getCause().getMessage();
    }

    public static BizException rethrowClientSideException(ExceptionEnum exceptionCode, Throwable cause) {
        if (cause == null) {
            BizException biz = new ClientSideException(exceptionCode);
            return biz;
        }
        return new ClientSideException(exceptionCode, cause);
    }

    public static BizException rethrowUnauthorizedException(ExceptionEnum exceptionCode, Throwable cause) {
        if (cause == null) {
            return new UnauthorizedException(exceptionCode);
        }
        return new UnauthorizedException(exceptionCode, cause);
    }

    public static BizException rethrowForbiddenException(ExceptionEnum exceptionCode, Throwable cause) {
        if (cause == null) {
            return new ForbiddenException(exceptionCode);
        }
        return new ForbiddenException(exceptionCode, cause);
    }

    public static BizException rethrowServerSideException(ExceptionEnum exceptionCode, Throwable cause) {
        if (cause == null) {
            return new ServerSideException(exceptionCode);
        }
        return new ServerSideException(exceptionCode, cause);
    }
}
