package github.candy.java.seek.knowledge.spring.autowired;

import github.candy.java.seek.knowledge.spring.autowired.constructorautowired.UserController;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoWiredTest {
    @Test
    public void constructorAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig .class);
        UserController userController = (UserController) applicationContext.getBean("constructorUserController");
        userController.addUser();
        applicationContext.close();
    }

    @Test
    public void filedAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig .class);
        github.candy.java.seek.knowledge.spring.autowired.filedautowired.UserController userController = (github.candy.java.seek.knowledge.spring.autowired.filedautowired.UserController) applicationContext.getBean("filedUserController");
        userController.addUser();
        applicationContext.close();
    }

    @Test
    public void setterAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig .class);
        github.candy.java.seek.knowledge.spring.autowired.setterautowired.UserController userController = (github.candy.java.seek.knowledge.spring.autowired.setterautowired.UserController) applicationContext.getBean("setterUserController");
        userController.addUser();
        applicationContext.close();
    }
}
