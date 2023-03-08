package com.komfortcieplny.FakeEmailSender.email.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Should Send Email To All Users")
    void sendEmailTest() throws Exception {
        this.mockMvc.perform(post("/api/v1/email")
                .contentType(APPLICATION_JSON)
                .content("{\"subject\":\"Subject\",\"message\":\"Message\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent to all users"));
    }

    @Test
    @DisplayName("Should Send Email To User With Id 1")
    void SendEmailToIdTest() throws Exception {
        this.mockMvc.perform(post("/api/v1/email/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content("{\"subject\":\"Subject\",\"message\":\"Message\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent to user with id 1"));
    }
}