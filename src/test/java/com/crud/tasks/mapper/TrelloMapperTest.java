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

    @Test
    void shouldMapToTrelloCardDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard trelloCard = new TrelloCard("test", "test2", "test3", "test4");

        TrelloCardDto expected = new TrelloCardDto("test", "test2", "test3", "test4");

        //When
        TrelloCardDto result = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals(expected, result);
    }

    @Test
    void shouldMapToTrelloListDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloList> trelloList = List.of(
                new TrelloList("test", "test2", true),
                new TrelloList("test2", "test3", true)
        );

        List<TrelloListDto> expected = List.of(
                new TrelloListDto("test", "test2", true),
                new TrelloListDto("test2", "test3", true)
        );

        //When
        List<TrelloListDto> result = trelloMapper.mapToListDto(trelloList);

        //Then
        assertEquals(expected, result);
    }

    @Test
    void shouldMapToBoardsDto() {
        //Given
        TrelloMapper trelloMapper = new TrelloMapper();
        List<TrelloBoard> trelloBoard = List.of(
                new TrelloBoard("1", "test", List.of()),
                new TrelloBoard("2", "test2", List.of())
        );

        List<TrelloBoardDto> expected = List.of(
                new TrelloBoardDto("1", "test", List.of()),
                new TrelloBoardDto("2", "test2", List.of())
        );

        //When
        List<TrelloBoardDto> result = trelloMapper.mapToBoardsDto(trelloBoard);

        //Then
        assertEquals(expected, result);
    }
}