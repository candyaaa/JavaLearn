package github.candy.java.seek.knowledge.spring.createBean.FactoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author candy
 * @date 2021/10/4 21:31
 */
@Component("schoolFactory")
public class SchoolFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new School();
    }

    @Override
    public Class<?> getObjectType() {
        return School.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
