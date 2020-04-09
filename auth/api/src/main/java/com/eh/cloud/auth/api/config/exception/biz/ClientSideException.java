package com.eh.cloud.auth.api.config.exception.biz;

import com.eh.cloud.auth.api.config.exception.BizException;
import com.eh.cloud.auth.model.constants.BaseErrors;
import org.springframework.http.HttpStatus;

/**
 * @author caopeihe
 * @Type ClientSideException.java
 * @Desc
 * @date 2020/2/27 17:48
 */
public class ClientSideException extends BizException {

    /**
     * 分场景定义了ClientSideException，ServerSideException，UnauthorizedException，ForbiddenException异常，分别表示客户端异常（400），服务端异常（500），未授权异常（401），禁止访问异常（403），如ClientSideException定义
     *
     *
     * 并且提供一个异常工具类ExceptionUtil，方便不同场景使用，
     *
     * rethrowClientSideException：抛出ClientSideException，将以status code 400返回客户端。由客户端引起的异常调用该方法，如参数校验失败。
     * rethrowUnauthorizedException： 抛出UnauthorizedException，将以status code 401返回客户端。访问未授权时调用，如token校验失败等。
     * rethrowForbiddenException： 抛出ForbidenException，将以status code 403返回客户端。访问被禁止时调用，如用户被禁用等。
     * rethrowServerSideException： 抛出ServerSideException，将以status code 500返回客户端。服务端引起的异常调用该方法，如调用第三方服务异常，数据库访问出错等。
     */

    public <E extends Enum<E> & BaseErrors> ClientSideException(E exceptionCode, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, exceptionCode, cause);
    }

    public <E extends Enum<E> & BaseErrors> ClientSideException(E exceptionCode) {
        super(HttpStatus.BAD_REQUEST, exceptionCode, null);
    }
}
