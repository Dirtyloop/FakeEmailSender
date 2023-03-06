package com.komfortcieplny.FakeEmailSender.user.service;

import com.komfortcieplny.FakeEmailSender.user.exceptions.UserNotFoundException;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        List<User> userList = userRepository.findAll();
        logger.info("All users displayed");
        return userList;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException(String.format("User with id %s was not found", id)));
        logger.info(String.format("User with id %s displayed", id));
        return user;
    }

    public User createUser(User user) {
        User newUser = userRepository.save(user);
        logger.info(String.format("User with id %s created", newUser.getId()));
        return newUser;
    }

    public User updateUser(User user) {
        User updatedUser = userRepository.save(user);
        logger.info(String.format("User with id %s updated", updatedUser.getId()));
        return updatedUser;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
        logger.info(String.format("User with id %s deleted", id));
    }
}
