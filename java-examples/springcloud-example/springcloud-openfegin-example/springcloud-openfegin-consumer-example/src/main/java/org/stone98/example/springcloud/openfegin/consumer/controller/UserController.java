package org.stone98.example.springcloud.openfegin.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stone98.example.springcloud.openfegin.consumer.client.model.Message;
import org.stone98.example.springcloud.openfegin.consumer.client.service.IMessageService;

import java.util.List;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年03月10日 16:35:12
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IMessageService messageSevice;

    @GetMapping("getMessageListByUserId/{userId}")
    public List<Message> getMessageListByUserId(@PathVariable("userId")Integer userId){
        return messageSevice.listMessageByUserId(userId);
    }
}
