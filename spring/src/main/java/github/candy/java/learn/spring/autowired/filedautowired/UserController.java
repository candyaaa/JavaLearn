package github.candy.java.learn.spring.autowired.filedautowired;

import github.candy.java.learn.spring.autowired.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("filedUserController")
public class UserController {

    @Autowired
    private UserService userService;

    public void addUser(){
        userService.addUser();
    }
}
