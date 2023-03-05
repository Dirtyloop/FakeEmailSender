package com.komfortcieplny.FakeEmailSender.user.controller;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        User newUser = userService.createUser(user);
        logger.info("User with name " + newUser.getName() + " created");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable("id") Long id) {
        if (user.getId()==null) {
            user.setId(id);
        }
        if (Objects.equals(user.getId(), id)) {
            User updatedUser = userService.updateUser(user);
            logger.info("User with id " + updatedUser.getId() + " updated");
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        logger.info("User with id " + id + " can not be updated with id " + user.getId());
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        logger.info("User with id " + id + " deleted");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
