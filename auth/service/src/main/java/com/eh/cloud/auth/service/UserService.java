package com.eh.cloud.auth.service;

import com.eh.cloud.auth.model.entity.user.SysUser;

/**
 * author: caopeihe
 * date: 2020/3/3 11:12
 * desc: UserService
 */
public interface UserService {
    SysUser findByCode(String code);

    SysUser findByName(String name);

    SysUser save(SysUser user);
}
