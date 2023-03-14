package org.stone98.example.springcloud.ribbon.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description: 用户相关接口
 * @Author: stone-98
 * @createTime: 2023年03月09日 14:13:52
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户注册
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        log.info("用户开始注册。");
        // 发送短信
        return restTemplate.getForEntity("http://springcloud-ribbon-producer/message/send", String.class).getBody();
    }
}
