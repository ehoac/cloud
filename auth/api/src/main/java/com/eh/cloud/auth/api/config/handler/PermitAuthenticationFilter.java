package com.eh.cloud.auth.api.config.handler;

import com.eh.cloud.auth.api.config.exception.biz.UnauthorizedException;
import com.eh.cloud.auth.api.util.ExceptionUtil;
import com.eh.cloud.auth.api.util.ResultUtil;
import com.eh.cloud.auth.model.constants.ExceptionEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author caopeihe
 * @Type PermitAuthenticationFilter.java
 * @Desc 自定义过滤器验证token 返回自定义数据格式
 * @date 2020/2/25 11:39
 */

@Component
public class PermitAuthenticationFilter extends OAuth2AuthenticationProcessingFilter {
    private Logger log = LoggerFactory.getLogger(PermitAuthenticationFilter.class);
    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";
    private TokenExtractor tokenExtractor = new BearerTokenExtractor();
    private boolean stateless = true;
    OAuth2AuthenticationManager oAuth2AuthenticationManager = new OAuth2AuthenticationManager();

    @Autowired
    private TokenStore tokenStore;

    public PermitAuthenticationFilter() {
        DefaultTokenServices dt = new DefaultTokenServices();
        dt.setTokenStore(tokenStore);
        oAuth2AuthenticationManager.setTokenServices(dt);
        this.setAuthenticationManager(oAuth2AuthenticationManager);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        log.info(" ================== =========================== ===================");
        log.info("当前访问的URL地址：" +request.getRequestURI());
        Authentication authentication = this.tokenExtractor.extract(request);
        if (authentication == null) {
            if (this.stateless && this.isAuthenticated()) {
                // SecurityContextHolder.clearContext();
            }
            log.info("当前访问的URL地址：" +request.getRequestURI() +"不进行拦截...");
            filterChain.doFilter(request, response);
        } else {
            log.info("************　开始验证token　..........................   ");
            String accessToken = request.getParameter("access_token");
            String headerToken = request.getHeader(HEADER_AUTHORIZATION);
            Map<String,String> map =  new HashMap<>(6);
            map.put("status","403");
            AtomicBoolean error = new AtomicBoolean(false);
            OAuth2AccessToken oAuth2AccessToken = null;
            if(StringUtils.isNotBlank(accessToken)){
                try {
                    oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
                    log.info("token =" +oAuth2AccessToken.getValue());
                }catch (InvalidTokenException e){
                    error.set(true);
                    map.put("message",e.getMessage());
                    log.info("** 无校的token信息.　** ");
                    throw ExceptionUtil.rethrowUnauthorizedException(ExceptionEnum.TOKEN_INVALID, e.getCause());
                    // throw new AccessDeniedException("无校的token信息.");
                }

            } else if (StringUtils.isNotBlank(headerToken) && headerToken.startsWith(BEARER_AUTHENTICATION)){
                try {
                    oAuth2AccessToken = tokenStore.readAccessToken(headerToken.split(" ")[1]);
//                    log.info("token =" +oAuth2AccessToken.getValue());
                }catch (InvalidTokenException e){
                    error.set(true);
                    map.put("message",e.getMessage());
                    log.info("** 无校的token信息.　** ");
                    throw new UnauthorizedException(ExceptionEnum.TOKEN_INVALID, e.getCause());
//                    throw new AccessDeniedException("无校的token信息.");
                }

            } else {
                error.set(true);
                map.put("message","参数无token.");
                log.info("** 参数无token.　** ");
                throw ExceptionUtil.rethrowUnauthorizedException(ExceptionEnum.NO_TOKEN, null);
            }

            if (oAuth2AccessToken == null) {
                throw new UnauthorizedException(ExceptionEnum.TOKEN_INVALID);
            } else {
                log.info("token =" +oAuth2AccessToken.getValue());
            }

            if (!error.get()){
                filterChain.doFilter(request, response);
            }else {
                map.put("path", request.getServletPath());
                map.put("timestamp", String.valueOf(LocalDateTime.now()));
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                ResultUtil.writeJavaScript(response, map);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && !(authentication instanceof AnonymousAuthenticationToken);
    }
}
