package com.eh.cloud.auth.api.config.handler;

import com.eh.cloud.auth.api.config.exception.biz.UnauthorizedException;
import com.eh.cloud.auth.model.constants.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author: caopeihe
 * date: 2020/3/3 10:02
 * desc: AccessDeniedHandler
 */
@Component
public class AccessDeniedHandler extends OAuth2AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse response,
                       AccessDeniedException ae){
        logger.info("用户访问 权限异常", ae);
        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        throw new UnauthorizedException(ExceptionEnum.USER_NO_ACCESS, ae.getCause());
    }

    public AccessDeniedHandler() {
        super();
    }

    @Override
    public void setExceptionTranslator(WebResponseExceptionTranslator<?> exceptionTranslator) {
        super.setExceptionTranslator(exceptionTranslator);
    }

    @Override
    public void setExceptionRenderer(OAuth2ExceptionRenderer exceptionRenderer) {
        super.setExceptionRenderer(exceptionRenderer);
    }

    @Override
    protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> result, Exception authException) {
        return super.enhanceResponse(result, authException);
    }
}
