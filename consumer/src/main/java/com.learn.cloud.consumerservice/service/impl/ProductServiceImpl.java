package com.learn.cloud.consumerservice.service.impl;

import com.learn.cloud.consumerservice.domain.Product;
import com.learn.cloud.consumerservice.repository.ProductRepository;
import com.learn.cloud.consumerservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    protected ProductRepository productRepository;

    @Override
    public List<Product> list(){
        return this.productRepository.findAll();
    }

    @Override
    public Product one(Long id){
        return this.productRepository.findOne(id);
    }
}
