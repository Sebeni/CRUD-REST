package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/v1/trello")
public class TrelloController {
    @Autowired
    TrelloClient trelloClient;
    
    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards(){
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        
        trelloBoards.forEach(trelloBoardDto -> System.out.println(trelloBoardDto));
    }
    
}
