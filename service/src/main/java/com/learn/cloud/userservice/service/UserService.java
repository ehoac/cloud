package com.learn.cloud.userservice.service;

import com.learn.cloud.userservice.domain.User;
import com.learn.cloud.userservice.domain.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface UserService {
    Page<User> getPage(Pageable pageable);
    User load(Long id);
    User save(UserDto userDto);
    void delete(Long id);
}
