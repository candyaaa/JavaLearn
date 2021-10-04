package github.candy.java.seek.knowledge.spring.life;

import github.candy.java.seek.knowledge.spring.life.test.Config;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class InitAndDestroyAnnotationTest {
    @Test
    public void InitAndDestroyAnnotationTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        applicationContext.close();
    }
}
