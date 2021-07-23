package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TrelloMapperTest {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    void testMapToBoars() {

        //Given
        List<TrelloBoardDto> trelloBoardDtoList = new ArrayList<>();

        List<TrelloListDto> trelloListDto = new ArrayList<>();

        TrelloListDto trelloListDto1 = new TrelloListDto("1", "name", true);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "name1", false);

        trelloListDto.add(trelloListDto1);
        trelloListDto.add(trelloListDto2);

        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("name", "1", trelloListDto);
        trelloBoardDtoList.add(trelloBoardDto);

        //Then
        List<TrelloBoard> list = trelloMapper.mapToBoards(trelloBoardDtoList);

        //When
        assertEquals("name", list.get(0).getLists().get(0).getName());
    }

    @Test
    void testMapToBoardsDto() {
        //Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();

        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name2", true);
        TrelloList trelloList1 = new TrelloList("2", "name22", true);
        trelloLists.add(trelloList);
        trelloLists.add(trelloList1);

        TrelloBoard trelloBoard = new TrelloBoard("2", "trello", trelloLists);

        trelloBoards.add(trelloBoard);

        //Then
        List<TrelloBoardDto> list = trelloMapper.mapToBoardsDto(trelloBoards);

        //When
        assertEquals("name22", list.get(0).getLists().get(1).getName());
    }

    @Test
    void testMapToList() {

        //Given
        List<TrelloListDto> trelloListDtos = new ArrayList<>();

        TrelloListDto trelloListDto = new TrelloListDto("1", "test", true);
        TrelloListDto trelloListDto1 = new TrelloListDto("2", "test1", true);

        trelloListDtos.add(trelloListDto);
        trelloListDtos.add(trelloListDto1);

        //Then
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);

        //When
        assertTrue(trelloLists.get(1).isClosed());
    }

    @Test
    void testMapToListDto() {

        //Given
        List<TrelloList> trelloLists = new ArrayList<>();
        TrelloList trelloList = new TrelloList("1", "name2", true);
        TrelloList trelloList1 = new TrelloList("2", "name22", true);
        trelloLists.add(trelloList);
        trelloLists.add(trelloList1);

        //Then
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);

        //When
        assertEquals("2", trelloListDtos.get(1).getId());
    }

    @Test
    void testMapToCardDto() {

        //Given
        TrelloCard trelloCard = new TrelloCard("test", "test", "test", "test");

        //Then
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //When
        assertEquals("test", trelloCardDto.getName());
    }

    @Test
    void testMapToCard() {

        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");

        //Then
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //When
        assertEquals("test", trelloCard.getDescription());
    }
}
