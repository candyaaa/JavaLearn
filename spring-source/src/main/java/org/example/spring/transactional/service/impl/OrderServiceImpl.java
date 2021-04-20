package org.example.spring.transactional.service.impl;

import org.example.spring.transactional.dao.GoodDao;
import org.example.spring.transactional.dao.OrderDao;
import org.example.spring.transactional.dao.UserDao;
import org.example.spring.transactional.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private GoodDao goodDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    public void order() {
        orderDao.insertOrder(1, 1);

        goodDao.loseRepertory(1);

        userDao.loseBalance(100);

        int i = 1/0;
    }
}
