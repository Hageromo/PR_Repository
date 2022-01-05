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
public class GitHubEntity {


    @JsonProperty("name")       //the name of the repository
    private String name;

    @JsonProperty("stargazers_count")       //number of repository stars
    private Integer stargazers_count;

    @JsonProperty("language")       //language used in repository
    private String language;

    @JsonProperty("size")
    private Integer size;       //size of the repository


}
