package com.learn.cloud.userservice.repository.ex.impl;

import com.learn.cloud.userservice.domain.User;
import com.learn.cloud.userservice.repository.ex.UserRepositoryEx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public class UserRepositoryImpl implements UserRepositoryEx {
    @PersistenceContext
    protected EntityManager entityManager;
    public List<User> findTopUser(int maxResult){
        Query query = this.entityManager.createQuery("from User");
        query.setMaxResults(maxResult);
        return query.getResultList();
    }
}
