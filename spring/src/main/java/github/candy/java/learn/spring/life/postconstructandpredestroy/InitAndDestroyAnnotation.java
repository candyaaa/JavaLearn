package github.candy.java.learn.spring.life.postconstructandpredestroy;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class InitAndDestroyAnnotation {
    @PostConstruct
    public void init(){
        System.out.println("init");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}
