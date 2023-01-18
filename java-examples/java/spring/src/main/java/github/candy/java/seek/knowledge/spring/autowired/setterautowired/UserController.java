package github.candy.java.seek.knowledge.spring.autowired.setterautowired;

import github.candy.java.seek.knowledge.spring.autowired.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("setterUserController")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser(){
        userService.addUser();
    }
}
