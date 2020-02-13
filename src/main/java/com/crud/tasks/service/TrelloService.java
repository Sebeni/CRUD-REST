package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.mail.Mail;
import com.crud.tasks.domain.trello.CreatedTrelloCard;
import com.crud.tasks.domain.trello.TrelloBoardDto;
import com.crud.tasks.domain.trello.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrelloService {
    private static final String SUBJECT = "Tasks: New Trello card";
    
    @Autowired
    private TrelloClient trelloClient;
    
    @Autowired
    private SimpleMailService emailService;
    
    @Autowired
    private AdminConfig adminConfig;
    
    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }
    
    public CreatedTrelloCard createTrelloCard(TrelloCardDto trelloCardDto) {
        CreatedTrelloCard newCard = trelloClient.createNewCard(trelloCardDto);

        Optional.ofNullable(newCard).ifPresent(createdTrelloCard -> emailService.send(new Mail(adminConfig.getAdminMail(),
                SUBJECT, "New card: " + createdTrelloCard.getName() + " has been created on your Trello account",
                null)));
        
        return newCard;
    }
}
