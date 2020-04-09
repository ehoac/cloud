package com.eh.cloud.auth.api.config.user;

import com.eh.cloud.auth.model.entity.user.Authority;
import com.eh.cloud.auth.model.entity.user.SysUser;
import com.eh.cloud.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ehoac
 */
@Service(value = "userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = userService.findByCode(username);
        if (user == null){
            throw new UsernameNotFoundException("登录用户名【"+username+"】不存在！");
        }
        return new SysUserDetails(user, getAuthority(user.getAuthorities()));
//        return new org.springframework.security.core.userdetails.User(user.getAccount(), user.getPwd(), getAuthority(user.getAuthorities()));
    }

    private Set getAuthority(List<Authority> authoities) {
        if (authoities == null || authoities.size() == 0) {
            return Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authoities.stream().map(authority -> new SimpleGrantedAuthority(authority.getCode())).collect(Collectors.toSet());
    }
}
