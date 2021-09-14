package github.candy.java.learn.spring.life.initialzingbeananddisposablebean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InitializingBeanAndDisposableBeanTest {
    @Test
    public void initializingBeanAndDisposableBeanTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitializingBeanAndDisposableBeanConfig.class);
        applicationContext.close();
    }
}
