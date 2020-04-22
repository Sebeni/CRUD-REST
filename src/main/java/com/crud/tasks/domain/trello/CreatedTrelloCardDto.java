package com.crud.tasks.domain.trello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedTrelloCardDto {
    
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("shortUrl")
    private String shortUrl;
    
    @JsonProperty("badges")
    private Badges badges;

    public CreatedTrelloCardDto(String id, String name, String shortUrl) {
        this.id = id;
        this.name = name;
        this.shortUrl = shortUrl;
    }

    public CreatedTrelloCardDto(String id, String name, String shortUrl, Badges badges) {
        this.id = id;
        this.name = name;
        this.shortUrl = shortUrl;
        this.badges = badges;
    }
}

