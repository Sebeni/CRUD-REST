package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.domain.trello.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TrelloServiceTest {

    @InjectMocks
    TrelloService trelloService;
    
    @Mock
    TrelloClient trelloClient;
    
    @Mock
    SimpleEmailService simpleEmailService;
    
    @Mock
    AdminConfig adminConfig;
    
    
    
    @Test
    void fetchTrelloBoards() {
        when(trelloClient.getTrelloBoards()).thenReturn(new ArrayList<>());

        List<TrelloBoardDto> fetchedBoardList = trelloService.fetchTrelloBoards();
        
        assertEquals(0, fetchedBoardList.size());
        verify(trelloClient, times(1)).getTrelloBoards();
    }

    @Test
    void createTrelloCard() {
        CreatedTrelloCardDto cardDto = new CreatedTrelloCardDto("1", "name", "shortUrl",
                new Badges(5, new AttachmentsByType()));
        TrelloCardDto trelloCardDto = new TrelloCardDto("name card", "description of card", "5", "6");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(cardDto);
        when(adminConfig.getAdminMail()).thenReturn("adminMail");
        doNothing().when(simpleEmailService).send(any(Mail.class));
        
        CreatedTrelloCardDto cardFromService = trelloService.createTrelloCard(trelloCardDto);
        
        assertAll(
                () -> assertEquals(cardDto.getId(), cardFromService.getId()),
                () -> assertEquals(cardDto.getName(), cardFromService.getName())
        );
        
        verify(trelloClient, times(1)).createNewCard(trelloCardDto);
        verify(simpleEmailService, times(1)).send(any(Mail.class));
        verify(adminConfig, atMostOnce()).getAdminMail();
    }
}