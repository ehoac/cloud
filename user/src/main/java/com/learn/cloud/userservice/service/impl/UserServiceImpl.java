package com.learn.cloud.userservice.service.impl;

import com.learn.cloud.userservice.domain.User;
import com.learn.cloud.userservice.domain.dto.UserDto;
import com.learn.cloud.userservice.repository.UserRepository;
import com.learn.cloud.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    protected UserRepository userRepository;

    @Override
    public Page<User> getPage(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    @Override
    public User load(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public User save(UserDto userDto) {
        User user = this.userRepository.findOne(userDto.getId());
        if (null == user){
            user = new User();
        }
        user.setNickname(userDto.getNickname());
        user.setAvatar(userDto.getAvatar());
        return this.userRepository.save(user);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void delete(Long id) {
        this.userRepository.delete(id);
    }
}
