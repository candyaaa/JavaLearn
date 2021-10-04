package github.candy.java.spring.createBean.case01;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author candy
 * @date 2021/10/4 21:32
 */
public class SchoolFactoryBeanTest {
    @Test
    public void test01(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SchoolFactoryBeanConfig.class);
        School school = (School) applicationContext.getBean("schoolFactory");
        System.out.println(school);
        SchoolFactoryBean schoolFactoryBean = (SchoolFactoryBean) applicationContext.getBean("&schoolFactory");
        System.out.println(schoolFactoryBean);
    }
}
