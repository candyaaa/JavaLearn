package github.candy.java.spring.transactional.service.impl;

import github.candy.java.spring.transactional.dao.GoodDao;
import github.candy.java.spring.transactional.dao.OrderDao;
import github.candy.java.spring.transactional.service.OrderService;
import github.candy.java.spring.transactional.dao.UserDao;
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
