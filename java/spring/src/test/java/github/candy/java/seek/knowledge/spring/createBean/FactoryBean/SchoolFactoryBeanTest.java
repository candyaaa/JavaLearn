package github.candy.java.seek.knowledge.spring.createBean.FactoryBean;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author candy
 * @date 2021/10/4 21:32
 */
public class SchoolFactoryBeanTest {
    @Test
    public void getBeanTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SchoolFactoryBeanConfig.class);
        School school = (School) applicationContext.getBean("schoolFactory");
        System.out.println(school);
        SchoolFactoryBean schoolFactoryBean = (SchoolFactoryBean) applicationContext.getBean("&schoolFactory");
        System.out.println(schoolFactoryBean);
    }
}
