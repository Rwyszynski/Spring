package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;

    @InjectMocks
    private TrelloService trelloService;

    @Test
    void shouldFetchTrelloBoards() {
        //Given
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "test", List.of());
        List<TrelloBoardDto> trelloBoardsDto = List.of(trelloBoardDto);

        //When
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardsDto);

        //Then
        assertEquals(List.of(trelloBoardDto), trelloService.fetchTrelloBoards());
    }


}