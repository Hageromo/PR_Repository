package com.example.Allegro.controller;

import com.example.Allegro.model.GitHubEntity;
import com.example.Allegro.model.GitHubLanguage;
import com.example.Allegro.model.GitHubStars;
import com.example.Allegro.service.ParsingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.MultimapBuilder;
import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/repos")
public class MainController {

    @Autowired
    private ParsingService parsingService;

    @GetMapping("/{user}")
    public List<GitHubEntity> getRepos(@PathVariable String user){

        ObjectMapper mapper = new ObjectMapper();
        List<GitHubEntity> repos = mapper.convertValue(parsingService.parse(getGitHubApi(user)), new TypeReference<List<GitHubEntity>>() {});

        List<GitHubEntity> repositoryDetails = new ArrayList<>();
        for(int i =0; i< repos.size(); i++){
            GitHubEntity entity = new GitHubEntity();
            entity.setName(repos.get(i).getName());
            entity.setLanguage(repos.get(i).getLanguage());
            entity.setStargazers_count(repos.get(i).getStargazers_count());
            entity.setSize(repos.get(i).getSize());
            repositoryDetails.add(i, entity);
        }
        return repositoryDetails;
    }


    @GetMapping("/{user}/stars")
    public GitHubStars getStars(@PathVariable String user){

        ObjectMapper mapper = new ObjectMapper();
        List<GitHubStars> repos = mapper.convertValue(parsingService.parse(getGitHubApi(user)), new TypeReference<List<GitHubStars>>() {});

        int count = 0;
        for(int i =0; i< repos.size(); i++){
            count = count + repos.get(i).getStargazers_count();
        }
        GitHubStars stars = new GitHubStars();
        stars.setStargazers_count(count);

        return stars;
    }

    @GetMapping("/{user}/languages")
    public List<GitHubLanguage> getLanguages(@PathVariable String user) throws JSONException {

        ObjectMapper mapper = new ObjectMapper();
        List<GitHubLanguage> repos = mapper.convertValue(parsingService.parse(getGitHubApi(user)), new TypeReference<List<GitHubLanguage>>() {});

        ArrayList<String> listOfAllLanguages = new ArrayList<>();
        ListMultimap<String, Integer> size = MultimapBuilder.hashKeys().linkedListValues().build();

        for(int i =0; i< repos.size(); i++){
            listOfAllLanguages.add(repos.get(i).getLanguage());     //list of every programming language used by user
            size.put(repos.get(i).getLanguage(), repos.get(i).getSize());
        }

        Map<String, Integer> map = Maps.transformValues(size.asMap(), ints ->       //sum of sizes in each language
                ints.stream().mapToInt(Integer::intValue).sum());


        Set<String> set = new HashSet<String>(listOfAllLanguages);
        List<GitHubLanguage> favouriteLanguages = new ArrayList<>();


         for (String r : set) {
            GitHubLanguage lang = new GitHubLanguage();
            lang.setLanguage(r);
            lang.setNumber_of_repositories(Collections.frequency(listOfAllLanguages, r));
            lang.setSize(map.get(r));
             favouriteLanguages.add(lang);
        }

        return favouriteLanguages;
    }

    public String getGitHubApi(String user){
        String url = "https://api.github.com/users/" + user + "/" + "repos";

        return url;
    }
}
