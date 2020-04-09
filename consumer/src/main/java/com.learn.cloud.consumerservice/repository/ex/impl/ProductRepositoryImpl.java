package com.learn.cloud.consumerservice.repository.ex.impl;

import com.learn.cloud.consumerservice.domain.Product;
import com.learn.cloud.consumerservice.repository.ex.ProductRepositoryEx;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public class ProductRepositoryImpl implements ProductRepositoryEx {
    @PersistenceContext
    protected EntityManager entityManager;
    @Override
    public List<Product> findTopProduct(int maxResult){
        Query query = this.entityManager.createQuery("from Product");
        query.setMaxResults(maxResult);
        return query.getResultList();
    }
}
