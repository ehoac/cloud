package com.eh.cloud.auth.api.util;

import com.eh.cloud.auth.api.config.exception.BizException;
import com.eh.cloud.auth.api.config.exception.biz.ClientSideException;
import com.eh.cloud.auth.api.config.exception.biz.ForbiddenException;
import com.eh.cloud.auth.api.config.exception.biz.ServerSideException;
import com.eh.cloud.auth.api.config.exception.biz.UnauthorizedException;
import com.eh.cloud.auth.model.constants.ExceptionEnum;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;

/**
 * @author caopeihe
 * @Type ExceptionUtil.java
 * @Desc
 * @date 2020/2/28 9:05
 */
public class ExceptionUtil {

    public static String extractStackTrace (Throwable error){
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement str: error.getStackTrace()) {
            if (str.toString().startsWith("com.eh.cloud.auth")) {
                sb.append(str.toString()).append("\n");
            }
        }
        return sb.toString();
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

    public static int getBaseErrorStatus(RequestAttributes requestAttributes) {
        Integer status = internalGetAttribute(requestAttributes, "javax.servlet.error.status_code");
        return (status == null ? HttpStatus.BAD_REQUEST.value() : status);
    }

    public static Throwable getDefaultErrorDetail(WebRequest webRequest) {
        Throwable error = new DefaultErrorAttributes().getError(webRequest);
        if (error != null) {
            while (error instanceof ServletException && error.getCause() != null) {
                error = error.getCause();
            }
        }
        return error;
    }

    public static String getDefaultErrorMessage(WebRequest webRequest) {
        Throwable error = getDefaultErrorDetail(webRequest);
        BindingResult result = internalExtractBindingResult(error);
        if (result == null) {
            return error.getMessage();
        }
        if (result.getErrorCount() > 0) {
            return "Validation failed for object='" + result.getObjectName()
                    + "'. Error count: " + result.getErrorCount();
        } else {
            return "No errors";
        }
    }

    public static String getDefaultErrorPath(WebRequest webRequest) {
        String str = webRequest.getDescription(true);
        String client = "";
        String path = "";
        if (str.contains(";")) {
            String[] args = str.split(";");
            if (args.length == 2) {
                client = args[1].split("=")[1];
                path = args[0].split("=")[1];
            }
        }

        return "http://" + client + path;
    }

    private static <T> T internalGetAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

    private static BindingResult internalExtractBindingResult(Throwable error) {
        if (error instanceof BindingResult) {
            return (BindingResult) error;
        }
        if (error instanceof MethodArgumentNotValidException) {
            return ((MethodArgumentNotValidException) error).getBindingResult();
        }
        return null;
    }
}
