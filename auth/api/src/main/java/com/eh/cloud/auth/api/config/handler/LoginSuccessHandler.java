package com.eh.cloud.auth.api.config.handler;

import com.eh.cloud.auth.api.util.HttpUtil;
import com.eh.cloud.auth.api.util.Result;
import com.eh.cloud.auth.model.entity.user.SysUser;
import com.eh.cloud.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * author: caopeihe
 * date: 2020/3/3 9:38
 * desc: LoginSuccessHandler
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Autowired
    private UserService userService;

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser user = userService.findByCode(userDetails.getUsername());

        response.setStatus(HttpStatus.OK.value());
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        this.saveLoginInfo(request, authentication);
        Result result = Result.success("success");
        response.getWriter().println(result.toJsonString());

//        logger.info("登录成功,即将forward: {}", this.defaultTargetUrl);
//        // 登录成功之后将SECURITY放入上下文中
//        request.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//        request.getRequestDispatcher(this.defaultTargetUrl).forward(request, response);
    }

    private void saveLoginInfo(HttpServletRequest request, Authentication authentication) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysUser user = userService.findByCode(userDetails.getUsername());
        String ip = HttpUtil.getIpAddress(request);
        user.setLastLogin(new Date());
        user.setLoginIp(ip);
        logger.info("登录用户: {}", user);
        this.userService.save(user);
    }
}