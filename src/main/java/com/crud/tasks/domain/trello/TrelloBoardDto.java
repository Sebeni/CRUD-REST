package com.crud.tasks.domain.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloBoardDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;
    
    
    @JsonProperty("lists")
    private List<TrelloListDto> lists;
    
 
}
