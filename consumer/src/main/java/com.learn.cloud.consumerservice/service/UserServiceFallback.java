package com.learn.cloud.consumerservice.service;

import com.learn.cloud.consumerservice.domain.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@SuppressWarnings("AlibabaServiceOrDaoClassShouldEndWithImpl")
public class UserServiceFallback implements UserService{
    @Override
    public List<UserDto> findAll(){
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(new UserDto(1L, "zhangSan_static", "/users/avatar/zhangsan.png"));
        return userDtos;
    }

    @Override
    public UserDto load(Long id){
        return new UserDto(1L, "zhangSan_static", "/users/avatar/zhangsan.png");
    }
}
