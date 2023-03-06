package com.komfortcieplny.FakeEmailSender.email.model;

public record EmailModel(String subject, String message) {
    @Override
    public String toString() {
        return "{subject: " + subject + ", message: " + message + "}";
    }
}
