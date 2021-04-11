package org.example.spring.autowired.filedautowired;

import org.example.spring.autowired.UserService;
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
