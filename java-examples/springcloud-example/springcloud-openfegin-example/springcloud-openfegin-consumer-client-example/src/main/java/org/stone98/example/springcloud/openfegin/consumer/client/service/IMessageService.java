package org.stone98.example.springcloud.openfegin.consumer.client.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.stone98.example.springcloud.openfegin.consumer.client.model.Message;

import java.util.List;

public interface IMessageService {
    @GetMapping("/message/{userId}")
    List<Message> listMessageByUserId(@PathVariable("userId") Integer userId);
}
