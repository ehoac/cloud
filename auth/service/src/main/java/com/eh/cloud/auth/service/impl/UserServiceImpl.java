package com.eh.cloud.auth.service.impl;

import com.eh.cloud.auth.model.entity.user.SysUser;
import com.eh.cloud.auth.service.UserService;
import com.eh.cloud.auth.mapper.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author: caopeihe
 * date: 2020/3/3 11:12
 * desc: UserServerImpl
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    public SysUser findByCode(String code){
        return userMapper.findByCode(code);
    }

    public SysUser findByName(String name){
        return userMapper.findByName(name);
    }

    public SysUser save(SysUser user){
        return userMapper.save(user);
    }
}
