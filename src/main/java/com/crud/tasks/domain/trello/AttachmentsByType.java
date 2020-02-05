package com.crud.tasks.domain.trello;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttachmentsByType {
    @JsonProperty("trello")
    private Trello trello;
}
