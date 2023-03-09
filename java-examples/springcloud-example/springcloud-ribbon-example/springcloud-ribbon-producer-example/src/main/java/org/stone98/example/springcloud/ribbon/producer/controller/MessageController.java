package org.stone98.example.springcloud.ribbon.producer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年03月09日 14:20:10
 */
@Slf4j
@RestController
@RequestMapping("message")
public class MessageController {
    @RequestMapping("send")
    public String sendMessage(){
        log.info("发送短信。");
        return "send ok";
    }
}
