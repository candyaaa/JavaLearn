package org.example.spring.autowired;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoWiredTest {
    @Test
    public void constructorAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig .class);
        org.example.spring.autowired.constructorautowired.UserController userController = (org.example.spring.autowired.constructorautowired.UserController) applicationContext.getBean("constructorUserController");
        userController.addUser();
        applicationContext.close();
    }

    @Test
    public void filedAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig .class);
        org.example.spring.autowired.filedautowired.UserController userController = (org.example.spring.autowired.filedautowired.UserController) applicationContext.getBean("filedUserController");
        userController.addUser();
        applicationContext.close();
    }

    @Test
    public void setterAutowiredTest() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutowiredConfig .class);
        org.example.spring.autowired.setterautowired.UserController userController = (org.example.spring.autowired.setterautowired.UserController) applicationContext.getBean("setterUserController");
        userController.addUser();
        applicationContext.close();
    }
}
