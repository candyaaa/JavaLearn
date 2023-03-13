package org.stone98.example.springcloud.openfegin.producer.clientimpl;


import org.springframework.web.bind.annotation.RestController;
import org.stone98.example.springcloud.openfegin.consumer.client.service.IMessageClient;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年03月10日 16:41:35
 */
@RestController
public class MessageController implements IMessageClient {
    @Override
    public String getFirstMessageByUserId(Integer userId) {
        return "你好~ 我是stone98，这是stone98的第一条消息~";
    }
}
