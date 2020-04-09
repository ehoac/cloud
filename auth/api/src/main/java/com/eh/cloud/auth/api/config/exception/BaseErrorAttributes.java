package com.eh.cloud.auth.api.config.exception;

import com.eh.cloud.auth.api.util.BeanUtil;
import com.eh.cloud.auth.api.util.ExceptionUtil;
import com.eh.cloud.auth.model.entity.common.ErrorResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author caopeihe
 * @Type BaseErrorAttributes.java
 * @Desc
 * @date 2020/2/27 17:25
 */
@Component
public class BaseErrorAttributes extends DefaultErrorAttributes {
    private boolean includeStackTrace;
    public BaseErrorAttributes() {
        super();
    }
    public BaseErrorAttributes(boolean includeStackTrace) {
        super();
        this.includeStackTrace = includeStackTrace;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        int status = ExceptionUtil.getBaseErrorStatus(webRequest);
        Throwable error = ExceptionUtil.getDefaultErrorDetail(webRequest);
        String message = "";
        String trace = "";
        if (error != null) {
            message = ExceptionUtil.getDefaultErrorMessage(webRequest);
            if (this.includeStackTrace) {
                trace = ExceptionUtil.extractStackTrace(error);
            }
        }
        String msgAttr = internalGetAttribute(webRequest, "javax.servlet.error.message");
        if ((!StringUtils.isEmpty(msgAttr) || "".equals(message))
                && !(error instanceof BindingResult)) {
            message = StringUtils.isEmpty(msgAttr) ? "No message available" : msgAttr;
        }
        String path = ExceptionUtil.getDefaultErrorPath(webRequest);
        ErrorResponse errorResponse = new ErrorResponse(status, message, trace, path);
        return BeanUtil.beanToMap(errorResponse);
    }

    private <T> T internalGetAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }
}
