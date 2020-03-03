package com.eh.cloud.auth.api.config.user;

import com.eh.cloud.auth.model.entity.user.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * author: caopeihe
 * date: 2020/3/3 11:17
 * desc: SysUserDetails
 */
public class SysUserDetails  implements UserDetails {
    private SysUser user;

    private Set<GrantedAuthority> authorityList;

    public SysUserDetails(SysUser user, Set<GrantedAuthority> authorityList) {
        this.user = user;
        this.authorityList = authorityList;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getCode();
    }

    // 账号到期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账号启用
    @Override
    public boolean isEnabled() {
        return true;
    }
}
