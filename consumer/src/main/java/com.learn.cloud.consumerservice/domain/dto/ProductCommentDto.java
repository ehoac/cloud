package com.learn.cloud.consumerservice.domain.dto;

import com.learn.cloud.consumerservice.domain.Product;
import com.learn.cloud.consumerservice.domain.ProductComment;

import java.util.Date;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
public class ProductCommentDto {
    private Long id;
    private Product product;
    private UserDto author;
    private String content;
    private Date created;

    public ProductCommentDto(ProductComment productComment) {
        this.id = productComment.getId();
        this.content = productComment.getContent();
        this.created = productComment.getCreated();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public UserDto getAuthor() {
        return author;
    }

    public void setAuthor(UserDto author) {
        this.author = author;
    }
}
