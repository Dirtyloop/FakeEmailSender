package com.komfortcieplny.FakeEmailSender.user.controller;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> userList = userService.findAllUsers();
        logger.info("All users displayed");
        return userList;
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") Long id) {
        User user = userService.findUser(id);
        logger.info("User with id " + id + " displayed");
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        logger.info("User with name " + user.getName() + " created");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
