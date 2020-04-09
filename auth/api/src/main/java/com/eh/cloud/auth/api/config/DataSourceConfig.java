package com.eh.cloud.auth.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author caopeihe
 * @Type DataSourceConfig.java
 * @Desc
 * @date 2020/2/28 15:20
 */
@Configuration
public class DataSourceConfig {
    /**
     * 事务配置
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // mybatis分页配置
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        Page
//        PaginationInterceptor page = new PaginationInterceptor();
//        page.setDialectType("mysql");
//        return page;
//    }
}
