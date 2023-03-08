package com.komfortcieplny.FakeEmailSender.user.service;

import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("Should Invoke userRepository.findAll")
    void getUsersSimpleTest() {
        userService.getUsers();

        verify(userRepository).findAll();
    }

    @Test
    @DisplayName("Should Return List With 3 Users")
    void shouldReturnListWith3UsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User(), new User()));

        assertThat(userService.getUsers()).hasSize(3);
    }


    @Test
    void getUser() {
    }

    @Test
    @DisplayName("Should Create A New User")
    void createUserTest() {
        User userToDB = User.builder().name("Michal").email("michal@example.com").build();
        when(userRepository.save(any(User.class))).thenReturn(userToDB);

        User userFromDB = userService.createUser(new User());

        assertThat(userFromDB).isEqualTo(userToDB);
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}