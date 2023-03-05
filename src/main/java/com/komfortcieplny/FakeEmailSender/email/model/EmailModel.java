package com.komfortcieplny.FakeEmailSender.email.model;

public record EmailModel(String recipient, String subject, String message) {
}
