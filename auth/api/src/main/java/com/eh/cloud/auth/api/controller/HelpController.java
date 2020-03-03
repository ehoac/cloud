package com.eh.cloud.auth.api.controller;


import com.eh.cloud.auth.api.config.exception.biz.ClientSideException;
import com.eh.cloud.auth.api.util.ExceptionUtil;
import com.eh.cloud.auth.api.util.UuidUtil;
import com.eh.cloud.auth.model.constants.ExceptionEnum;
import com.eh.cloud.auth.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author caopeihe
 * @Type AuthController.java
 * @Desc
 * @date 2020/2/26 8:44
 */
@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    private UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("generate/{type}")
    public String uuid(@PathVariable String type, @Nullable String plain) {
        if (StringUtils.equals("uuid", type)) {
            return UuidUtil.nextId();
        } else if (StringUtils.equals("pwd", type)) {
            if (StringUtils.isEmpty(plain)) {
                return "param [plain] can not be null!";
            }
            return passwordEncoder.encode(plain);
        }
        throw ExceptionUtil.rethrowClientSideException(ExceptionEnum.ILLEGAL_ARGUMENT, null);
    }

    @GetMapping("register")
    public void createUser(){

        throw new ClientSideException(ExceptionEnum.INTER_ERROR);
    }
}
