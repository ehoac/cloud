package com.learn.cloud.userservice.domain.dto;

import com.learn.cloud.userservice.domain.User;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public class UserDto {
    private Long id;
    private String nickname;
    private String avatar;

    public UserDto(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.avatar = user.getAvatar();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
