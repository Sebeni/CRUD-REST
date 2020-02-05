package com.crud.tasks.domain.trello;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Trello {
    
    @JsonProperty("board")
    private int board;
    @JsonProperty("card")
    private int card;
}
