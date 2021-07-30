package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private  AdminConfig adminConfig;

    @InjectMocks
    private TrelloService trelloService;

    @Test
    void testCreateTrelloCard() {
        //Given
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("test", "test", "test");
        TrelloCardDto trelloCardDto = new TrelloCardDto("test", "test", "test", "test");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        //when & then
        CreatedTrelloCardDto createdTrelloCardDto1 = trelloService.createTrelloCard(trelloCardDto);

        assertEquals("test", createdTrelloCardDto1.getId());
    }
}