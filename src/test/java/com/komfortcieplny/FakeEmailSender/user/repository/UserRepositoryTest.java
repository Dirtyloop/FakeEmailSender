package com.komfortcieplny.FakeEmailSender.user.repository;

import com.komfortcieplny.FakeEmailSender.user.exceptions.UserNotFoundException;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.repository.UserRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("There Should Be 5 Users In Database")
    public void getUsersShouldReturn5UsersTest() {
        List<User> users = userRepository.findAll();
        int expectedUserNumber = 5;

        assertEquals(expectedUserNumber, users.size(), "There are 5 users in database");
    }

}
