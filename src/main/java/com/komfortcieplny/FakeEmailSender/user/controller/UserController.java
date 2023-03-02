package com.komfortcieplny.FakeEmailSender.user.controller;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User("Michal", "michal.nowakowski85@gmail.com"));
        userList.add(new User("Joanna", "zmyslonyadres@pl"));
        return userList;
    }
}
