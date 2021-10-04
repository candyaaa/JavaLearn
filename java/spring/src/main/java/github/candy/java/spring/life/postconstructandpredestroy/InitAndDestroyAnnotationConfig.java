package github.candy.java.spring.life.postconstructandpredestroy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("org.example.spring.life.postconstructandpredestroy")
@Configuration
public class InitAndDestroyAnnotationConfig {

    @Bean
    public InitAndDestroyAnnotation initAndDestroyAnnotation(){
        return new InitAndDestroyAnnotation();
    }
}
