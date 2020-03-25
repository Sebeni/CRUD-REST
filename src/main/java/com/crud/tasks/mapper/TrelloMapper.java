package com.crud.tasks.mapper;

import com.crud.tasks.domain.trello.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToTrelloBoardList(List<TrelloBoardDto> trelloDtoBoardList) {
        return trelloDtoBoardList.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());
    }

    public TrelloBoard mapToBoard(TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToTrelloList(trelloBoardDto.getLists()));
    }
    
    public List<TrelloList> mapToTrelloList(List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(tlDto -> new TrelloList(tlDto.getId(), tlDto.getName(), tlDto.isClosed()))
                .collect(Collectors.toList());
    }
    
    public List<TrelloBoardDto> mapToTrelloBoardDtoList(List<TrelloBoard> trelloBoardList) {
        return trelloBoardList.stream()
                .map(trelloBoard -> mapToBoardDto(trelloBoard))
                .collect(Collectors.toList());
    }
    
    public TrelloBoardDto mapToBoardDto(TrelloBoard trelloBoard) {
        return new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToTrelloListDto(trelloBoard.getLists()));
    }
    
    public List<TrelloListDto> mapToTrelloListDto(List<TrelloList> trelloList) {
        return trelloList.stream()
                .map(tl -> new TrelloListDto(tl.getId(), tl.getName(), tl.isClosed()))
                .collect(Collectors.toList());
    }
    
    public TrelloCardDto mapToTrelloCardDto(TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }
    
    public TrelloCard mapToTrelloCard(TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
    
}
