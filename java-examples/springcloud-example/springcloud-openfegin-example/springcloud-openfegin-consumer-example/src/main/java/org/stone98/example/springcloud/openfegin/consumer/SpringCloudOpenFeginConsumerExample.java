package org.stone98.example.springcloud.openfegin.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudOpenFeginConsumerExample
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringCloudOpenFeginConsumerExample.class, args);
    }
}
