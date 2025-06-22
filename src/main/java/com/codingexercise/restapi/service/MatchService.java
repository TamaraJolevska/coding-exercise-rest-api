package com.codingexercise.restapi.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.codingexercise.restapi.client.MatchClient;
import com.codingexercise.restapi.model.Match;

@Service
public class MatchService {

    private final MatchClient matchClient;
    private List<Match> allMatches;

    public MatchService(MatchClient matchClient) {
        this.matchClient = matchClient;
        this.allMatches = matchClient.fetchAllMatches();
    }

    public List<Match> getAllMatches() {
        return allMatches;
    }

    public List<Match> getMatchesByStatus(String status) {
        return allMatches.stream().filter(match -> match.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }

    public List<Match> getMatchesByTeam(String teamName) {
        return allMatches.stream().filter(match -> match.getHomeTeam().contains(teamName) || match.getAwayTeam().contains(teamName)).collect(Collectors.toList());
    }
}
