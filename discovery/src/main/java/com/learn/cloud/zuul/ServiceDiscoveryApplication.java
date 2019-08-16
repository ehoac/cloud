package com.learn.cloud.zuul;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServiceDiscoveryApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceDiscoveryApplication.class).web(true).run(args);
//        SpringApplication.run(ServiceDiscoveryApplication.class, args);
    }

}
