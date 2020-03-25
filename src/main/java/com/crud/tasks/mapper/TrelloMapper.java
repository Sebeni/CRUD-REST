package com.crud.tasks.mapper;

import com.crud.tasks.domain.trello.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloMapper {

    public List<TrelloBoard> mapToBoardList(List<TrelloBoardDto> trelloDtoBoardList) {
        return trelloDtoBoardList.stream()
                .map(this::mapToBoard)
                .collect(Collectors.toList());
    }

    public TrelloBoard mapToBoard(TrelloBoardDto trelloBoardDto) {
        return new TrelloBoard(trelloBoardDto.getId(), trelloBoardDto.getName(), mapToTrelloListCollection(trelloBoardDto.getLists()));
    }
    
    public List<TrelloList> mapToTrelloListCollection(List<TrelloListDto> trelloListDto) {
        return trelloListDto.stream()
                .map(tlDto -> new TrelloList(tlDto.getId(), tlDto.getName(), tlDto.isClosed()))
                .collect(Collectors.toList());
    }
    
    public List<TrelloBoardDto> mapToBoardDtoList(List<TrelloBoard> trelloBoardList) {
        return trelloBoardList.stream()
                .map(this::mapToBoardDto)
                .collect(Collectors.toList());
    }
    
    public TrelloBoardDto mapToBoardDto(TrelloBoard trelloBoard) {
        return new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(), mapToTrelloListDtoCollection(trelloBoard.getLists()));
    }
    
    public List<TrelloListDto> mapToTrelloListDtoCollection(List<TrelloList> trelloList) {
        return trelloList.stream()
                .map(tl -> new TrelloListDto(tl.getId(), tl.getName(), tl.isClosed()))
                .collect(Collectors.toList());
    }
    
    public TrelloCardDto mapToCardDto(TrelloCard trelloCard) {
        return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(), trelloCard.getPos(), trelloCard.getListId());
    }
    
    public TrelloCard mapToCard(TrelloCardDto trelloCardDto) {
        return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(), trelloCardDto.getPos(), trelloCardDto.getListId());
    }
    
}
