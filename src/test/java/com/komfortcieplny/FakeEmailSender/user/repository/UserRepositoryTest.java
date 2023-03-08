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

    @Test
    @DisplayName("newUser Saved In Database Should Have Id=6")
    @Order(3)
    public void saveNewUserToDatabaseTest() {
        User newUser = User.builder()
                .name("Boguś")
                .email("bogus@example.com")
                .build();
        Long expectedId = 6l;

        userRepository.save(newUser);

        assertEquals(expectedId, newUser.getId(), "newUser saved in database have id 6");
    }

    @Test
    @DisplayName("Another newUser Saved In Database Should Have Id=7")
    @Order(4)
    public void saveAnotherNewUserToDatabaseTest() {
        User newUser2 = User.builder()
                .name("Boguslaw")
                .email("bogus@example.com")
                .build();
        Long expectedId2 = 7l;

        userRepository.save(newUser2);

        assertEquals(expectedId2, newUser2.getId(), "Another newUser saved in database have id 7");
    }

    @Test
    @DisplayName("Should Throw UserNotFoundException When User Is Not Found")
    @Order(5)
    public void shouldThrowExceptionWhenUserIsNotFound() {
        String expectedMessage = "User with id 3 was not found";
        String actualMessage = "";
        Long userIdToDelete = 3l;

        userRepository.deleteById(userIdToDelete);

        try{
            User user = userRepository.findById(userIdToDelete).orElseThrow(() ->
                    new UserNotFoundException(String.format("User with id %s was not found", userIdToDelete)));
        } catch (UserNotFoundException e) {
            actualMessage = e.getMessage();
        }

        assertTrue(actualMessage.contains(expectedMessage), "User with id 3 is not present in database");
    }

    @Test
    @DisplayName("After Create newUser There Should Be 6 Users In Database")
    @Order(6)
    public void afterCreateNewUsergetUsersShouldReturn6UsersTest() {
        User newUser = User.builder()
                .name("Boguś")
                .email("bogus@example.com")
                .build();

        userRepository.save(newUser);
        List<User> usersList = userRepository.findAll();
        int expectedUserNumber = 6;

        assertEquals(expectedUserNumber, usersList.size());
    }
}
