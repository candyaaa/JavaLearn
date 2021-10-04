package github.candy.java.spring.autowired;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public void addUser(){
        System.out.println("add user...");
    }
}
