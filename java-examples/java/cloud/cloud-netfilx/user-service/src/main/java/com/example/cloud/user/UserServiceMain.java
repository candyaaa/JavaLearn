package com.example.cloud.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class UserServiceMain {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceMain.class, args);
    }

}
