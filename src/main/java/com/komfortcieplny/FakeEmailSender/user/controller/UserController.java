package com.komfortcieplny.FakeEmailSender.user.controller;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);
    private List<User> userList = new ArrayList<>();


    @GetMapping("/users")
    public List<User> getUsers() {
        userList.add(new User("Michal", "michal.nowakowski85@gmail.com"));
        userList.add(new User("Joanna", "zmyslonyadres@pl"));
        logger.info("Users " + userList.get(0).getName() + " and " + userList.get(1).getName() + " displayed");
        return userList;
    }
}
