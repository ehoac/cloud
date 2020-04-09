package com.learn.cloud.userservice.repository.ex;

import com.learn.cloud.userservice.domain.User;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface UserRepositoryEx {
    List<User> findTopUser(int maxResult);
}
