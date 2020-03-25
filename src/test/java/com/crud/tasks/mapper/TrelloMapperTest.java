package com.crud.tasks.mapper;

import com.crud.tasks.domain.trello.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloMapperTest {
    @Autowired
    private TrelloMapper trelloMapper;
    
    private static TrelloCard tCard;
    private static List<TrelloList> tListCollection;
    private static TrelloList tList;
    private static TrelloBoard tBoard;
    private static TrelloCardDto tCardDto;
    private static List<TrelloListDto> tListDtoCollection;
    private static TrelloListDto tListDto;
    private static TrelloBoardDto tBoardDto;
    private static List<TrelloBoard> tBoardList;
    private static List<TrelloBoardDto> tBoardDtoList;

    @BeforeAll
    public static void init() {
        tCard = new TrelloCard("card", "t card", "1", "1");
        tListCollection = new ArrayList<>();
        tList = new TrelloList("2", "to Do", false);
        tListCollection.add(tList);
        tBoardList = new ArrayList<>();
        tBoard = new TrelloBoard("3", "t board", tListCollection);
        tBoardList.add(tBoard);
        
        tCardDto = new TrelloCardDto("card Dto", "t cardDto", "4", "4");
        tListDtoCollection = new ArrayList<>();
        tListDto = new TrelloListDto("5", "done", true);
        tListDtoCollection.add(tListDto);
        tBoardDtoList = new ArrayList<>();
        tBoardDto = new TrelloBoardDto("6", "t board dto", tListDtoCollection);
        tBoardDtoList.add(tBoardDto);
    }
    
    
    @Test
    void mapToTrelloBoardList() {
        List<TrelloBoard> tBoardListFromMapper = trelloMapper.mapToBoardList(tBoardDtoList);
        
        assertAll(
                () -> assertEquals(tBoardDtoList.size(), tBoardListFromMapper.size()),
                () -> assertEquals(tBoardDtoList.get(0).getId(), tBoardListFromMapper.get(0).getId())
        );
    }

    @Test
    void mapToBoard() {
        TrelloBoard tBoardFromMapper = trelloMapper.mapToBoard(tBoardDto);

        assertAll(
                () -> assertEquals(tBoardDto.getId(), tBoardFromMapper.getId()),
                () -> assertEquals(tBoardDto.getName(), tBoardFromMapper.getName()),
                () -> assertEquals(tBoardDto.getLists().size(), tBoardFromMapper.getLists().size()),
                () -> assertEquals(tBoardDto.getLists().get(0).getId(), tBoardFromMapper.getLists().get(0).getId())
        );
    }

    @Test
    void mapToTrelloListCollection() {
        List<TrelloList> tListCollection = trelloMapper.mapToTrelloListCollection(tListDtoCollection);

        assertAll(
                () -> assertEquals(tListDtoCollection.get(0).getId(), tListCollection.get(0).getId()),
                () -> assertEquals(tListDtoCollection.get(0).getName(), tListCollection.get(0).getName())
        );
    }

    @Test
    void mapToTrelloBoardDtoList() {
        List<TrelloBoardDto> tBoardDtoListFromMapper = trelloMapper.mapToBoardDtoList(tBoardList);

        assertAll(
                () -> assertEquals(tBoardList.get(0).getId(), tBoardDtoListFromMapper.get(0).getId()),
                () -> assertEquals(tBoardList.get(0).getName(), tBoardDtoListFromMapper.get(0).getName()),
                () -> assertEquals(tBoardList.get(0).getLists().get(0).getId(), tBoardDtoListFromMapper.get(0).getLists().get(0).getId()),
                () -> assertEquals(tBoardList.get(0).getLists().get(0).getName(), tBoardDtoListFromMapper.get(0).getLists().get(0).getName())
        );
    }

    @Test
    void mapToBoardDto() {
        TrelloBoardDto tBoardDtoFromMapper = trelloMapper.mapToBoardDto(tBoard);

        assertAll(
                () -> assertEquals(tBoard.getId(), tBoardDtoFromMapper.getId()),
                () -> assertEquals(tBoard.getName(), tBoardDtoFromMapper.getName()),
                () -> assertEquals(tBoard.getLists().get(0).getId(), tBoardDtoFromMapper.getLists().get(0).getId()),
                () -> assertEquals(tBoard.getLists().get(0).getName(), tBoardDtoFromMapper.getLists().get(0).getName())
        );
        
    }

    @Test
    void mapToTrelloListDtoCollection() {
        List<TrelloListDto> tListDtoCollectionFromMapper = trelloMapper.mapToTrelloListDtoCollection(tListCollection);

        assertAll(
                () -> assertEquals(tListDtoCollectionFromMapper.get(0).getId(), tListCollection.get(0).getId()),
                () -> assertEquals(tListDtoCollectionFromMapper.get(0).getName(), tListCollection.get(0).getName())
        );
    }

    @Test
    void mapToTrelloCardDto() {
        TrelloCardDto tCardDtoFromMapper = trelloMapper.mapToCardDto(tCard);

        assertAll(
                () -> assertEquals(tCard.getDescription(), tCardDtoFromMapper.getDescription()),
                () -> assertEquals(tCard.getListId(), tCardDtoFromMapper.getListId()),
                () -> assertEquals(tCard.getName(), tCardDtoFromMapper.getName()),
                () -> assertEquals(tCard.getPos(), tCardDtoFromMapper.getPos())
        );
    }

    @Test
    void mapToTrelloCard() {
        TrelloCard tCardFromMapper = trelloMapper.mapToCard(tCardDto);

        assertAll(
                () -> assertEquals(tCardDto.getDescription(), tCardFromMapper.getDescription()),
                () -> assertEquals(tCardDto.getListId(), tCardFromMapper.getListId()),
                () -> assertEquals(tCardDto.getName(), tCardFromMapper.getName()),
                () -> assertEquals(tCardDto.getPos(), tCardFromMapper.getPos())
        );
    }
}