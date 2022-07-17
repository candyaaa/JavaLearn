package github.candy.java.seek.knowledge.spring.config;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author candy
 * @date 2021/9/5 17:15
 */
public class ConfigTest {
    @Test
    public void constructorAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        DatabaseConfig config = applicationContext.getBean(DatabaseConfig.class);
        System.out.println(config.toString());
        applicationContext.close();
    }
}
