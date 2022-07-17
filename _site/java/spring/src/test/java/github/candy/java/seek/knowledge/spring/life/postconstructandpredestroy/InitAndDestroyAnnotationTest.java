package github.candy.java.seek.knowledge.spring.life.postconstructandpredestroy;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InitAndDestroyAnnotationTest {
    @Test
    public void InitAndDestroyAnnotationTest(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(InitAndDestroyAnnotationConfig.class);
        applicationContext.close();
    }
}
