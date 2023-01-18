package github.candy.java.seek.knowledge.spring.autowired.filedautowired;

import github.candy.java.seek.knowledge.spring.autowired.UserService;
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
