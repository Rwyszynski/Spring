package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrelloCardDtoTest {

    @Test
    public void testToString() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("Card 1", "test", "test", "1");

        // When
        String expectedToString = "TrelloCardDto(name=Card 1, description=test, pos=test, listId=1)";

        // Then
        assertEquals(expectedToString, cardDto.toString());
    }
}