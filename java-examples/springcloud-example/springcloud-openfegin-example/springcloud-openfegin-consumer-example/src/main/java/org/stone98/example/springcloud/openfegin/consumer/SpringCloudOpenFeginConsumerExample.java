package org.stone98.example.springcloud.openfegin.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.stone98.example.springcloud.openfegin.consumer.client")
public class SpringCloudOpenFeginConsumerExample
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringCloudOpenFeginConsumerExample.class, args);
    }
}
