package github.candy.java.seek.knowledge.spring.life.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author candy
 * @date 2021/10/4 11:13
 */
@Configuration
public class Config {
    @Bean
    public PostProcessPropertyValues postProcessPropertyValues(){
        return new PostProcessPropertyValues();
    }

    @Bean
    public User user(){
        return new User();
    }

}
