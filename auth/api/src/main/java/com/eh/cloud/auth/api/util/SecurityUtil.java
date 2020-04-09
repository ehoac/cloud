package com.eh.cloud.auth.api.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author caopeihe
 * @Type SecurityUtil.java
 * @Desc
 * @date 2020/2/28 15:05
 */
public class SecurityUtil{
    /**
     * 当前登录用户
     * @return the current login name
     */
    public static UserDetails getCurrentLoginName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        }
        return null;
    }
}
