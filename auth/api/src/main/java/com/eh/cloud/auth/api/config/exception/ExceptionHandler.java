package com.eh.cloud.auth.api.config.exception;

import com.eh.cloud.auth.model.constants.ResponseConstant;
import com.eh.cloud.auth.model.entity.common.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author caopeihe
 * @Type GlobalExceptionHandler.java
 * @Desc
 * @date 2020/2/27 16:48
 *
 * DefaultHandlerExceptionResolver： 这个是默认实现，处理Spring定义的各种标准异常，将其转换为对应的Http Status Code，具体处理的异常参考 doResolveException 方法
 * ResponseStatusExceptionResolver：用来支持@ResponseStatus注解使用的实现，如果自定义的异常通过@ResponseStatus注解进行了修饰，并且容器中存在ResponseStatusExceptionResolver的bean，则自定义异常抛出时会被该bean进行处理，返回注解定义的Http Status Code及内容给客户端
 * ExceptionHandlerExceptionResolver：用来支持@ExceptionHandler注解使用的实现，使用该注解修饰的方法来处理对应的异常。不过该注解的作用范围只在controller类，如果需要全局处理，则需要配合@ControllerAdvice注解使用。
 * SimpleMappingExceptionResolver：将异常映射为视图
 * HandlerExceptionResolverComposite：就是各类实现的组合，依次执行，只要其中一个处理返回不为null，则不再处理。
 */
@RestControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private boolean includeStackTrace;

    public ExceptionHandler(boolean includeStackTrace){
        super();
        this.includeStackTrace = includeStackTrace;
    }

    public ExceptionHandler(){

    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BizException.class)
    public ResponseEntity<Object> handleBizException(BizException ex) {
        logger.warn("catch biz exception: " + ex.toString(), ex.getCause());
        return this.asResponseEntity(HttpStatus.valueOf(ex.getStatus()), ex.getCode(), ex.getMsg(), ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(Exception ex) {
        logger.warn("catch illegal exception.", ex);
        return this.asResponseEntity(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.name().toLowerCase(), ex.getMessage(), ex);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        logger.error("catch exception.", ex);
        return this.asResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.name().toLowerCase(), ResponseConstant.ERROR_MESSAGE, ex);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        logger.warn("catch uncustom exception.", ex);
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(new ErrorResponse(status.value(), exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }
        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + Objects.requireNonNull(exception.getParameter().getMethod()).getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());
            return new ResponseEntity<>(new ErrorResponse(status.value(), "参数转换失败"), status);
        }
        return this.asResponseEntity(status, status.name().toLowerCase(), ex.getMessage(), ex);
    }

    /**
     * 捕获  RuntimeException 异常
     *
     * @param request  request
     * @param e        exception
     * @param response response
     * @return 响应结果
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ErrorResponse runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        RuntimeException exception = (RuntimeException) e;
        String url = request.getRequestURI();
        return new ErrorResponse(400, exception.getMessage(), url);
    }

    protected ResponseEntity<Object> asResponseEntity(HttpStatus status, String errorCode, String errorMessage, Exception ex) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put(BizException.HTTP_STATUS, status.value());
        data.put(BizException.ERROR_CODE, errorCode);
        data.put(BizException.ERROR_MESSAGE, errorMessage);
        //是否包含异常的stack trace
        if(includeStackTrace){
            addStackTrace(data, ex);
        }
        return new ResponseEntity<>(data, status);
    }

    private void addStackTrace(Map<String, Object> errorAttributes, Throwable error) {
        StringWriter stackTrace = new StringWriter();
        error.printStackTrace(new PrintWriter(stackTrace));
        stackTrace.flush();
        errorAttributes.put(BizException.ERROR_TRACE, stackTrace.toString());
    }
}
