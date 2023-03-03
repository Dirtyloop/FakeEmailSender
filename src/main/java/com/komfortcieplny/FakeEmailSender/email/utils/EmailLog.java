package com.komfortcieplny.FakeEmailSender.email.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class EmailLog {

    private static final String NEW_LINE = System.lineSeparator();
    Path filePath = Path.of("./logs/emails.log");

    public boolean logInfo(String log) throws IOException {

        Files.writeString(filePath, log + NEW_LINE, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        return true;
    }
}
