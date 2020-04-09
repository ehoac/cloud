package com.learn.cloud.consumerservice.service.impl;

import com.learn.cloud.consumerservice.domain.ProductComment;
import com.learn.cloud.consumerservice.repository.ProductCommentRepository;
import com.learn.cloud.consumerservice.service.ProductCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Service
public class ProductCommmentServiceImpl implements ProductCommentService {
    @Autowired
    protected ProductCommentRepository productCommentRepository;

    @Override
    public List<ProductComment> findByProductIdOrderByCreated(Long id){
        return this.productCommentRepository.findByProductIdOrderByCreated(id);
    }
}
