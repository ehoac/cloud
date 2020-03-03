package com.eh.cloud.auth.api.config;

import com.eh.cloud.auth.api.config.handler.AccessDeniedHandler;
import com.eh.cloud.auth.api.config.handler.AuthenticationEntryPoint;
import com.eh.cloud.auth.api.config.handler.LogoutSuccessHandler;
import com.eh.cloud.auth.api.config.handler.PermitAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

/**
 * @author caopeihe
 * @Type ResourceServerConfiguration.java
 * @Desc
 * @date 2020/2/25 12:00
 */
@Configuration
@EnableResourceServer
@Order(6)
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "*";
    @Autowired
//    private CustomTokenServices tokenServices;
    private DefaultTokenServices tokenServices;
    @Autowired
    private PermitAuthenticationFilter permitAuthenticationFilter;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID).stateless(true)
                .tokenServices(tokenServices)
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置那些资源需要保护的
        http.csrf().disable()
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated();
        //自定义token过滤 token校验失败后自定义返回数据格式
        http.addFilterBefore(permitAuthenticationFilter, AbstractPreAuthenticatedProcessingFilter.class);

    }


    /**
     * 重写 token 验证失败后自定义返回数据格式
     * @return
     */
//    @Bean
//    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
//        return new DefaultWebResponseExceptionTranslator() {
//            @Override
//            public ResponseEntity translate(Exception e) throws Exception {
//                ResponseEntity responseEntity = super.translate(e);
//                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
//                HttpHeaders headers = new HttpHeaders();
//                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
//                // do something with header or response
//                if(401==responseEntity.getStatusCode().value()){
//                    //自定义返回数据格式
//                    Map<String,String> map =  new HashMap<>();
//                    map.put("status","401");
//                    map.put("message",e.getMessage());
//                    map.put("timestamp", String.valueOf(LocalDateTime.now()));
//                    return new ResponseEntity(JSON.toJSONString(map), headers, responseEntity.getStatusCode());
//                }else{
//                    return new ResponseEntity(body, headers, responseEntity.getStatusCode());
//                }
//            }
//        };
//    }
}
