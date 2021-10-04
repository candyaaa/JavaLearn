package github.candy.java.seek.knowledge.spring.life.beanpostprocessor;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanPostProcessorTest {
    @Test
    public void beanPostProcessorTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(BeanPostProcessorConfig.class);
        applicationContext.close();
    }
}
