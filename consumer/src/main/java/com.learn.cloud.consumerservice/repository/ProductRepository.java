package com.learn.cloud.consumerservice.repository;

import com.learn.cloud.consumerservice.domain.Product;
import com.learn.cloud.consumerservice.repository.ex.ProductRepositoryEx;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryEx {

}
