package github.candy.java.spring.life.initialzingbeananddisposablebean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class InitializingBeanAndDisposableBean implements InitializingBean, DisposableBean {
    public void destroy() throws Exception {
        System.out.println("destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
    }
}
