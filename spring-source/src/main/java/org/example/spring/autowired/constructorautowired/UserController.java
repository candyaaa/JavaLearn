package org.example.spring.autowired.constructorautowired;

import org.example.spring.autowired.UserService;
import org.springframework.stereotype.Component;

@Component("constructorUserController")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void addUser(){
        userService.addUser();
    }
}
