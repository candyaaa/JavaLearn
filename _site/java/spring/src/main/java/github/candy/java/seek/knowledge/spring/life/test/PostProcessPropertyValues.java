package github.candy.java.seek.knowledge.spring.life.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

import java.beans.PropertyDescriptor;

/**
 * @author candy
 * @date 2021/10/4 11:06
 */
public class PostProcessPropertyValues extends InstantiationAwareBeanPostProcessorAdapter {
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        return super.postProcessPropertyValues(pvs, pds, bean, beanName);
    }
}
