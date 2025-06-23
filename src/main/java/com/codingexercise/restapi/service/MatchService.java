package com.codingexercise.restapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.codingexercise.restapi.client.MatchClient;
import com.codingexercise.restapi.model.Match;

@Service
public class MatchService {

    private static final Logger logger = LoggerFactory.getLogger(MatchService.class);

    private final MatchClient matchClient;
    private List<Match> allMatches;

    public MatchService(MatchClient matchClient) {
        this.matchClient = matchClient;
        refreshMatches();
    }

    @Scheduled(cron = "0 * * * * *")
    public void refreshMatches() {
        this.allMatches = matchClient.fetchAllMatches();
        logger.debug("Data refreshed " + LocalDateTime.now());
    }

    @Cacheable("matches")
    public List<Match> getAllMatches() {
        logger.debug("Fetching data without cache.");
        return allMatches;
    }

    public List<Match> getMatchesByStatus(String status) {
        return allMatches.stream().filter(match -> match.getStatus().equalsIgnoreCase(status)).collect(Collectors.toList());
    }

    public List<Match> getMatchesByTeam(String teamName) {
        return allMatches.stream().filter(match -> match.getHomeTeam().contains(teamName) || match.getAwayTeam().contains(teamName)).collect(Collectors.toList());
    }
}
