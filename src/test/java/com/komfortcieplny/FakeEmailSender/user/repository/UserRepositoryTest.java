package com.komfortcieplny.FakeEmailSender.user.repository;

import com.komfortcieplny.FakeEmailSender.user.exceptions.UserNotFoundException;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("There Should Be 5 Users In Database")
    @Order(1)
    public void getUsersShouldReturn5UsersTest() {
        List<User> users = userRepository.findAll();
        int expectedUserNumber = 5;

        assertEquals(expectedUserNumber, users.size(), "There are 5 users in database");
    }

    @Test
    @DisplayName("User With Id=5 Should Have Name Joanna")
    @Order(2)
    public void userWithId5ShouldHaveNameJoanna() {
        User user5 = userRepository.findById(5l).get();
        String expectedName = "Joanna";

        assertEquals(expectedName, user5.getName(), "User with id 5 have name Joanna");
    }

}
