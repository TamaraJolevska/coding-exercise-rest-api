package com.codingexercise.restapi.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codingexercise.restapi.model.Match;

import java.util.Arrays;
import java.util.List;

@Service
public class MatchClient {
    @Value("${external.api.base.url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Match> fetchAllMatches() {
        String fullUrl = baseUrl + "/match/all";
        Match[] matches = restTemplate.getForObject(fullUrl, Match[].class);
        return Arrays.asList(matches);
    }
}
