package com.crud.tasks.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloBoardTest {

    @Test
    public void testGetLists() {
        //Given
        TrelloList list1 = new TrelloList();
        TrelloList list2 = new TrelloList();
        List<TrelloList> expectedLists = Arrays.asList(list1, list2);

        //When
        TrelloBoard board = new TrelloBoard("boardId", "Test Board", expectedLists);
        List<TrelloList> actualLists = board.getLists();

        //Then
        assertEquals(expectedLists, actualLists);
    }
}