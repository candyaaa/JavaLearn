package github.candy.java.seek.knowledge.spring.transactional.service.impl;

import github.candy.java.seek.knowledge.spring.transactional.dao.GoodDao;
import github.candy.java.seek.knowledge.spring.transactional.dao.OrderDao;
import github.candy.java.seek.knowledge.spring.transactional.dao.UserDao;
import github.candy.java.seek.knowledge.spring.transactional.service.OrderService;
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
