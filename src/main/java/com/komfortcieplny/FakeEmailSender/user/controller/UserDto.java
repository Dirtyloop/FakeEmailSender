package com.komfortcieplny.FakeEmailSender.user.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserDto {
    @NotBlank
    private String name;
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "{name = " + name + ", email=" + email + "}";
    }
}
