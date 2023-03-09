package com.komfortcieplny.FakeEmailSender.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Contains;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RequestLoggerTest {

    private RequestLogger requestLogger = new RequestLogger();

    @Test
    @DisplayName("Should Return True After Log Info")
    void shouldReturnTrueAfterLogInfoTest() throws IOException {
        String message = "Test message";

        assertTrue(requestLogger.logInfo(message));
    }

    @Test
    @DisplayName("Should Contains Logged Message")
    void shouldContainsLoggedMessageTest() throws IOException {
        String generatedString = RandomStringUtils.randomAlphabetic(16);
        Path filePath = Path.of("./logs/requests.log");
        String fileContent = Files.readString(filePath);

        requestLogger.logInfo(generatedString);

        assertTrue(Files.readString(filePath).contains(generatedString));

        Files.writeString(filePath, fileContent, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

    @Test
    @DisplayName("Should Contains Logged Date And Time")
    void shouldContainsLoggedDateAndTimeTest() throws IOException {
        String generatedString = RandomStringUtils.randomAlphabetic(16);
        Path filePath = Path.of("./logs/requests.log");
        String fileContent = Files.readString(filePath);
        String dateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        requestLogger.logInfo(generatedString);

        assertTrue(Files.readString(filePath).contains(dateTimeNow));

        Files.writeString(filePath, fileContent, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }
}