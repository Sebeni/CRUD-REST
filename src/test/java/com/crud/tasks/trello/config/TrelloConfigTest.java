package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {
    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void getTrelloApiEndpoint() {
        assertNotNull(trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    void getTrelloAppKey() {
        assertNotNull(trelloConfig.getTrelloAppKey());
    }

    @Test
    void getTrelloToken() {
        assertNotNull(trelloConfig.getTrelloToken());
    }

    @Test
    void getTrelloUsername() {
        assertNotNull(trelloConfig.getTrelloUsername());
    }
}