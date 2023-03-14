package org.stone98.example.springcloud.openfegin.consumer.client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "IMessageService", name = "springcloud-openfegin-producer")
public interface IMessageClient {
    @GetMapping("/message/{userId}")
    String getFirstMessageByUserId(@PathVariable("userId") Integer userId);
}
