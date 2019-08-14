package com.learn.cloud.consumerservice.repository.ex;

import com.learn.cloud.consumerservice.domain.Product;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface ProductRepositoryEx {
    List<Product> findTopProduct(int maxResult);
}
