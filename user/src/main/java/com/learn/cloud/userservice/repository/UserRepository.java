package com.learn.cloud.userservice.repository;

import com.learn.cloud.userservice.domain.User;
import com.learn.cloud.userservice.repository.ex.UserRepositoryEx;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryEx {

}
