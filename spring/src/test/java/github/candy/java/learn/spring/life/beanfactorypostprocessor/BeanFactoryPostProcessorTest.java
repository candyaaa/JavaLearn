package github.candy.java.learn.spring.life.beanfactorypostprocessor;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanFactoryPostProcessorTest {
    @Test
    public void BeanFactoryPostProcessorTest(){
        AnnotationConfigApplicationContext applicationContext  = new AnnotationConfigApplicationContext(BeanFactoryPostProcessorConfig.class);
        applicationContext.close();
    }
}
