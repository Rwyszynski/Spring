package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    @Test
    void shouldMapToBoards() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoardDto> trelloBoardDtos = List.of(
                new TrelloBoardDto("3", "test", List.of(new TrelloListDto("3", "test2", true))),
                new TrelloBoardDto("4", "test2", List.of(new TrelloListDto("4", "test5", true)))
        );

        List<TrelloBoard> expected = List.of(
                new TrelloBoard("3", "test", List.of(new TrelloList("3", "test2", true))),
                new TrelloBoard("4", "test2", List.of(new TrelloList("4", "test5", true)))
        );
        //When

        List<TrelloBoard> result = trelloMapper.mapToBoards(trelloBoardDtos);

        //Then
        assertEquals(expected, result);

    }

    @Test
    void shouldMapToTrelloList() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();

        List<TrelloListDto> trelloListDtos = List.of(
                new TrelloListDto("1","testtest", true),
                new TrelloListDto("2","testtest2", true)
        );

        List<TrelloList> expected = List.of(
                new TrelloList("1", "testtest", true),
                new TrelloList("2", "testtest2", true)
        );

        //When
        List<TrelloList> result = trelloMapper.mapToList(trelloListDtos);

        //Then
        assertEquals(expected, result);
    }

    @Test
    void shouldMapToTrelloCard() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test2", "test3", "test4");

        TrelloCard expected = new TrelloCard("test", "test2", "test3", "test4");

        //When
        TrelloCard result = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals(expected, result);
    }
}