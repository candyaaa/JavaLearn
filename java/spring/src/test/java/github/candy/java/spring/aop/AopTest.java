package github.candy.java.spring.aop;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigOfAOP.class);

        //1、不要自己创建对象
//		MathCalculator mathCalculator = new MathCalculator();
//		mathCalculator.div(1, 1);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        String[] beanNamesForType = applicationContext.getBeanNamesForType(MathCalculator.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }
        mathCalculator.div(1, 1);

    }
}
