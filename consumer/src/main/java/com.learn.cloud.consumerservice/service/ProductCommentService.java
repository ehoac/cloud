package com.learn.cloud.consumerservice.service;

import com.learn.cloud.consumerservice.domain.ProductComment;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public interface ProductCommentService {
    public List<ProductComment> findByProductIdOrderByCreated(Long id);
}
