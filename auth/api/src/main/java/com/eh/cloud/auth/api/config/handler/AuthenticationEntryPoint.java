package com.eh.cloud.auth.api.config.handler;

import com.eh.cloud.auth.api.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * author: caopeihe
 * date: 2020/3/3 10:05
 * desc: AuthenticationEntryPoint
 */
@Component
public class AuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint {
    private Logger log = LoggerFactory.getLogger(AuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info(" ====================================================== ");
        log.info("请求url：" +httpServletRequest.getRequestURI());
        log.info("  ============ 身份认证失败..................... ");
        log.info(e.getMessage());
        log.info(e.getLocalizedMessage());
        e.printStackTrace();
        Map<String,String> map =  new HashMap<>(6);
        map.put("status","401");
        map.put("message",e.getMessage());
        map.put("path", httpServletRequest.getServletPath());
        map.put("timestamp", String.valueOf(LocalDateTime.now()));
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        ResultUtil.writeJavaScript(httpServletResponse, map);
    }

}