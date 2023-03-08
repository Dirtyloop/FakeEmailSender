package com.komfortcieplny.FakeEmailSender.user.controller;

import com.komfortcieplny.FakeEmailSender.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.aMapWithSize;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should Return All Users")
    void getUsersTest() throws Exception {
        this.mockMvc.perform(get("/api/v1/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[2].id").value(3))
                .andExpect(jsonPath("$.[3].id").value(4))
                .andExpect(jsonPath("$.[4].id").value(5));
    }

    @Test
    @DisplayName("Should Return User With Id 5")
    void getUserWithId5Test() throws Exception {
        this.mockMvc.perform(get("/api/v1/users/{id}", 5))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Joanna"))
                .andExpect(jsonPath("$.email").value("joanna@example.com"));
    }

    @Test
    @DisplayName("Should Create New User With Id 6")
    void createUserTest() throws Exception {
        this.mockMvc.perform(post("/api/v1/users")
                .contentType(APPLICATION_JSON)
                .content("{\"name\":\"Apolonia\",\"email\":\"apolonia@example.com\"}"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$", aMapWithSize(3)))
                .andExpect(jsonPath("$.id").value(6));

        Assertions.assertThat(this.userRepository.findAll()).hasSize(6);
    }

    @Test
    @DisplayName("Should Not Create New User When Name Is Blank")
    void shouldNotCreateUserWhenNameIsBlankTest() throws Exception {
        this.mockMvc.perform(post("/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"   \",\"email\":\"user@example.com\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Assertions.assertThat(this.userRepository.findAll()).hasSize(5);
    }

    @Test
    @DisplayName("Should Not Create New User When Email Is Invalid")
    void shouldNotCreateUserWhenEmailIsInvalidTest() throws Exception {
        this.mockMvc.perform(post("/api/v1/users")
                        .contentType(APPLICATION_JSON)
                        .content("{\"name\":\"Bogus\",\"email\":\"bogus\"}"))
                .andDo(print())
                .andExpect(status().isBadRequest());

        Assertions.assertThat(this.userRepository.findAll()).hasSize(5);
    }

    @Test
    @DisplayName("Should Update User With Id 3")
    void updateUserTest() throws Exception {
        this.mockMvc.perform(put("/api/v1/users/{id}", 3)
                .contentType(APPLICATION_JSON)
                .content("{\"name\":\"Janina\",\"email\":\"janina@example.com\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.name").value("Janina"))
                .andExpect(jsonPath("$.email").value("janina@example.com"));

        Assertions.assertThat(this.userRepository.findAll()).hasSize(5);
    }

    @Test
    @DisplayName("Should Delete User With Id 4")
    void deleteUserTest() throws Exception {
        this.mockMvc.perform(delete("/api/v1/users/{id}", 4))
                .andDo(print())
                .andExpect(status().isNoContent());

        Assertions.assertThat(this.userRepository.findAll()).hasSize(4);
    }
}