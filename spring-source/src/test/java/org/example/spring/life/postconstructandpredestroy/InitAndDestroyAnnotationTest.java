package org.example.spring.life.postconstructandpredestroy;

import org.example.spring.life.beanpostprocessor.BeanPostProcessorConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InitAndDestroyAnnotationTest {
    @Test
    public void InitAndDestroyAnnotationTest(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitAndDestroyAnnotationConfig.class);
        applicationContext.close();
    }
}
