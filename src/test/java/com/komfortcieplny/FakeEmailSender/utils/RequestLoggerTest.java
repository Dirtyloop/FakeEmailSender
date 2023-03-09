package com.komfortcieplny.FakeEmailSender.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class RequestLoggerTest {

    private RequestLogger requestLogger = new RequestLogger();

    @Test
    @DisplayName("Should Return True After Log Info")
    void shouldReturnTrueAfterLogInfoTest() throws IOException {
        String message = "Test message";

        assertTrue(requestLogger.logInfo(message));
    }
}