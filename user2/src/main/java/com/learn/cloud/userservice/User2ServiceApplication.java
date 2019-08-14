package com.learn.cloud.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class User2ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(User2ServiceApplication.class, args);
    }

}
