package github.candy.java.learn.spring.life.initialzingbeananddisposablebean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("org.example.spring.life.initialzingbeananddisposablebean")
public class InitializingBeanAndDisposableBeanConfig {
    @Bean
    public InitializingBeanAndDisposableBean initializingBeanAndDisposableBean() {
        return new InitializingBeanAndDisposableBean();
    }
}
