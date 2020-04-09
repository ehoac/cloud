package com.learn.cloud.userservice.domain;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @SequenceGenerator(initialValue = 1, name="userSeq", sequenceName = "USER_SEQUENCE")
    private Long id;
    private String nickname;
    private String avatar;

    public String toString(){
        return MoreObjects.toStringHelper(this)
                .add("id", getId()).toString();
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
