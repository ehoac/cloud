package com.learn.cloud.consumerservice.domain.dto;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public class UserDto {
    private Long id;
    private String nickname;
    private String avatar;
    private int userServicePort;

    public UserDto(){}
    public UserDto(Long id, String nickname, String avatar) {
        this.id = id;
        this.nickname = nickname;
        this.avatar = avatar;
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

    public int getUserServicePort() {
        return userServicePort;
    }

    public void setUserServicePort(int userServicePort) {
        this.userServicePort = userServicePort;
    }
}
