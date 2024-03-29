package com.komfortcieplny.FakeEmailSender.user.controller;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.service.UserService;
import com.komfortcieplny.FakeEmailSender.utils.RequestLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserService.class);

    private final UserService userService;
    private final RequestLogger requestLogger;

    public UserController(UserService userService, RequestLogger requestLogger) {
        this.userService = userService;
        this.requestLogger = requestLogger;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getUsers() {
        try {
            requestLogger.logInfo("getUsers requested");
        } catch (IOException e) {
            logger.error("Exception! getUsers request was not logged");
        }
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUser(@PathVariable("id") Long id) {
        try {
            requestLogger.logInfo(String.format("getUser with id %d requested", id));
        } catch (IOException e) {
            logger.error(String.format("Exception! getUser with id %d request was not logged%n", id));
        }
        return userService.getUser(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody @Valid UserDto userDto) {
        try {
            requestLogger.logInfo(
                    String.format(
                            "createUser %s requested",
                            userDto.toString()
                    )
            );
        } catch (IOException e) {
            logger.error("Exception! createUser request was not logged");
        }
        return userService.createUser(mapUser(userDto, null));
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody @Valid UserDto userDto, @PathVariable("id") Long id) {
        try {
            requestLogger.logInfo(
                    String.format(
                            "updateUser with id %d with %s requested",
                            id,
                            userDto.toString()
                    )
            );
        } catch (IOException e) {
            logger.error("Exception! updateUser request was not logged");
        }
        return userService.updateUser(mapUser(userDto, id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") Long id) {
        try {
            requestLogger.logInfo(String.format("deleteUser with id %d requested", id));
        } catch (IOException e) {
            logger.error(String.format("Exception! deleteUser with id %d request was not logged%n", id));
        }
        userService.deleteUser(id);
    }

    private static User mapUser(UserDto userDto, Long id) {
        return User.builder()
                .id(id)
                .name(userDto.getName())
                .email(userDto.getEmail())
                .build();
    }
}
