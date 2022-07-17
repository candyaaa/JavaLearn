package github.candy.java.seek.knowledge.spring.transactional;

import github.candy.java.seek.knowledge.spring.transactional.service.impl.OrderServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TransactionalTest {

    private final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(TransactionalConfig.class);

    @Test
    public void txTest() {
        OrderServiceImpl orderService = (OrderServiceImpl) applicationContext.getBean("orderServiceImpl");
        orderService.order();
    }
}

