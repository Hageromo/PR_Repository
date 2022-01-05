package com.example.Allegro.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitHubLanguage {

    @JsonProperty("language")
    private String language;


    @JsonProperty("number_of_repositories")
    private Integer number_of_repositories;

    @JsonProperty("size")
    private Integer size;
}
