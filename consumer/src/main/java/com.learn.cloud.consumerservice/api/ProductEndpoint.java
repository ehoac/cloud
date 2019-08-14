package com.learn.cloud.consumerservice.api;

import com.learn.cloud.consumerservice.domain.Product;
import com.learn.cloud.consumerservice.domain.ProductComment;
import com.learn.cloud.consumerservice.domain.dto.UserDto;
import com.learn.cloud.consumerservice.domain.dto.ProductCommentDto;
import com.learn.cloud.consumerservice.service.ProductCommentService;
import com.learn.cloud.consumerservice.service.ProductService;
import com.learn.cloud.consumerservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@RestController
@RequestMapping("/products")
public class ProductEndpoint {
    protected Logger logger = LoggerFactory.getLogger(ProductEndpoint.class);
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCommentService productCommentService;
    @Autowired
    @Qualifier(value = "restTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    public List<Product> list(){
        return this.productService.list();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Product detail(@PathVariable Long id){
        return this.productService.one(id);
    }

    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
    public List<ProductCommentDto> comments(@PathVariable Long id){
        List<ProductComment> commentList = this.productCommentService.findByProductIdOrderByCreated(id);
        if(null == commentList || commentList.isEmpty()){
            return Collections.emptyList();
        }
        return commentList.stream().map((comment)->{
            ProductCommentDto dto = new ProductCommentDto(comment);
            dto.setProduct(this.productService.one(comment.getProductId()));
            dto.setAuthor(this.userService.load(comment.getAuthorId()));
//            dto.setAuthor(this.loadUser(comment.getAuthorId()));
            return dto;
        }).collect(Collectors.toList());
    }


    protected UserDto loadUser(Long userId){
        UserDto userDto = this.restTemplate.getForEntity("http://USERSERVICE/users/{id}",
                UserDto.class, userId).getBody();
        if (null != userDto){
            this.logger.debug("I came from server : {}", userDto.getUserServicePort());
        }
        return userDto;
    }
}
