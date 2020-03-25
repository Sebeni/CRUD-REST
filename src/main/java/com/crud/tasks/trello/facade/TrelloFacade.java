package com.crud.tasks.trello.facade;

import com.crud.tasks.domain.trello.*;
import com.crud.tasks.mapper.TrelloMapper;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {

    
    @Autowired
    private TrelloValidator trelloValidator;
    @Autowired
    private TrelloService trelloService;
    @Autowired
    private TrelloMapper trelloMapper;
    
    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> fetchedTrelloBoards =  trelloMapper.mapToTrelloBoardList(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(fetchedTrelloBoards);
        return trelloMapper.mapToTrelloBoardDtoList(filteredBoards);
    }

    private List<TrelloBoard> validateTrelloBoards(List<TrelloBoard> fetchedTrelloBoards) {
        return trelloValidator.validateTrelloBoards(fetchedTrelloBoards);
    }

    public CreatedTrelloCardDto createCard(TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToTrelloCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToTrelloCardDto(trelloCard));
    }

    private void validateCard(TrelloCard trelloCard) {
        trelloValidator.validateCard(trelloCard);
    }

}
