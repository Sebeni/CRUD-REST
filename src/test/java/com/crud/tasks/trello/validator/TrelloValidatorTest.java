package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.trello.TrelloBoard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloValidatorTest {

    @Autowired
    TrelloValidator trelloValidator;
    
    @Test
    void shouldReturnOneBoard() {
        List<TrelloBoard> fetchedTrelloBoards = new ArrayList<>();
        fetchedTrelloBoards.add(new TrelloBoard("1", "regular name", new ArrayList<>()));
        fetchedTrelloBoards.add(new TrelloBoard("2", "TEST", new ArrayList<>()));
        fetchedTrelloBoards.add(new TrelloBoard("3", "TeST", new ArrayList<>()));
        fetchedTrelloBoards.add(new TrelloBoard("4", "test", new ArrayList<>()));
        
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(fetchedTrelloBoards);
        
        assertAll(
                () -> assertEquals(1, filteredBoards.size()),
                () -> assertFalse(fetchedTrelloBoards.get(0).getName().equalsIgnoreCase("test"))
        );
        
    }
}