package com.learn.cloud.userservice.api;

import com.learn.cloud.userservice.domain.User;
import com.learn.cloud.userservice.domain.dto.UserDto;
import com.learn.cloud.userservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="ehoac@sina.com">ehoac</a>
 */
@RestController
@RequestMapping("/users")
@Api(value = "UserEndpoint", description = "用户管理Api")
public class UserEndpoint {
    @Value("${server.port}")
    private int serverPort;
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "用户分页数据", notes = "用户分页数据", httpMethod = "GET", tags = "用户管理Api")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页， 从0开始，默认为第0页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size"),
            @ApiImplicitParam(name = "sort")
    })
    public List<UserDto> findAll(Pageable pageable) {
        Page<User> page = this.userService.getPage(pageable);
        if (null != page){
            return page.getContent().stream().map((user)->{
                return new UserDto(user, serverPort);
            }).collect(Collectors.toList());
        }
        return Collections.EMPTY_LIST;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto detail(@PathVariable Long id){
        User user = this.userService.load(id);
        return null != user ? new UserDto(user, serverPort):null;
    }
}
