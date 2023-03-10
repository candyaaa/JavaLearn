package org.stone98.example.springcloud.openfegin.producer.clientimpl;


import org.springframework.stereotype.Service;
import org.stone98.example.springcloud.openfegin.consumer.client.model.Message;
import org.stone98.example.springcloud.openfegin.consumer.client.service.IMessageService;

import java.util.List;

/**
 * @Description:
 * @Author: stone-98
 * @createTime: 2023年03月10日 16:41:35
 */
@Service
public class MessageServiceImpl implements IMessageService {
    @Override
    public List<Message> listMessageByUserId(Integer userId) {
        return null;
    }
}
