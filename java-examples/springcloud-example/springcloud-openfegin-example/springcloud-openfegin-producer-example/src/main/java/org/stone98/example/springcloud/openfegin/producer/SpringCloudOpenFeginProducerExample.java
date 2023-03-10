package org.stone98.example.springcloud.openfegin.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudOpenFeginProducerExample
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringCloudOpenFeginProducerExample.class, args);
    }
}
