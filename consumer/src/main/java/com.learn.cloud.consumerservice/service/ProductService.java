package com.learn.cloud.consumerservice.service;

import com.learn.cloud.consumerservice.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface ProductService {
    List<Product> list();
    Product one(Long id);
}
