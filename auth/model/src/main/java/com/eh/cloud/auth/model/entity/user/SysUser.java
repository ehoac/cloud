package com.eh.cloud.auth.model.entity.user;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * author: caopeihe
 * date: 2020/3/3 9:47
 * desc: SysUser
 */
@Data
public class SysUser implements Serializable{
    private String code;
    private String name;
    private String pwd;
    private String email;
    private String mobile;
    private String address;
    private Date lastLogin;
    private String loginIp;
    private List<Authority> authorities;
}
