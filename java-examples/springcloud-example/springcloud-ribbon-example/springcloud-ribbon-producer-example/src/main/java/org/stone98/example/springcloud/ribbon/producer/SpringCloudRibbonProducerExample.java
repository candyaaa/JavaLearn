package org.stone98.example.springcloud.ribbon.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudRibbonProducerExample {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudRibbonProducerExample.class, args);
    }
}
