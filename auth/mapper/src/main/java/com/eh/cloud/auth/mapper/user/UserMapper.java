package com.eh.cloud.auth.mapper.user;

import com.eh.cloud.auth.model.entity.user.SysUser;
import org.apache.ibatis.annotations.Select;

/**
 * author: caopeihe
 * date: 2020/3/3 9:56
 * desc: UserMapper
 */
public interface UserMapper {

    @Select("select * from tb_user where code=#{code}")
    SysUser findByCode(String code);

    @Select("select * from tb_user where name=#{name}")
    SysUser findByName(String name);

    SysUser save(SysUser user);
}
