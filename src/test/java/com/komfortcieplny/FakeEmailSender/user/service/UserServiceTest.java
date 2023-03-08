package com.komfortcieplny.FakeEmailSender.user.service;

import com.komfortcieplny.FakeEmailSender.user.exceptions.UserNotFoundException;
import com.komfortcieplny.FakeEmailSender.user.model.User;
import com.komfortcieplny.FakeEmailSender.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should Return List With 3 Users")
    void shouldReturnListWith3UsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User(), new User()));

        assertThat(userService.getUsers()).hasSize(3);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should Return One User")
    void getUserTest() {
        User userToDB = User.builder().name("Bogus").email("bogus@example.com").build();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userToDB));

        User userFromDB = userService.getUser(6l);

        assertThat(userFromDB).isEqualTo(userToDB);
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should Throw Exception When User Not Found")
    void shouldThrowExceptionWhenUserNotFoundTest() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUser(1l));
        verify(userRepository, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Should Create A New User")
    void createUserTest() {
        User userToDB = User.builder().name("Michal").email("michal@example.com").build();
        when(userRepository.save(any(User.class))).thenReturn(userToDB);

        User userFromDB = userService.createUser(new User());

        assertThat(userFromDB).isEqualTo(userToDB);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void updateUser() {
    }

    @Test
    @DisplayName("Should delete user")
    void deleteUserTest() {
        doNothing().when(userRepository).deleteById(anyLong());

        userService.deleteUser(3l);
        verify(userRepository, times(1)).deleteById(anyLong());
    }
}