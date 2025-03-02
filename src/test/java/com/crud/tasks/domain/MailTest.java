package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailTest {

    @Test
    public void testToString() {
        // Given
        Mail mail = new Mail.MailBuilder()
                .mailTo("test@example.com")
                .subject("Test")
                .message("test message")
                .toCc("cc@example.com")
                .build();

        // When
        String expectedToString = "Mail{mailTo='test@example.com', subject='Test', message='test message', toCc='cc@example.com'}";

        // Then
        assertEquals(expectedToString, mail.toString());
    }
}