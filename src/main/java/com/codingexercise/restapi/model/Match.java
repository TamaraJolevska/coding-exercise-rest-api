package com.codingexercise.restapi.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {
    @JsonProperty("status")
    private String status;
    @JsonProperty("home_team")
    private String homeTeam;
    @JsonProperty("away_team")
    private String awayTeam;
    @JsonProperty("start_time")
    private Instant startTime;
    @JsonProperty("home_score")
    private Long homeScore;
    @JsonProperty("away_score")
    private Long awayScore;

    public Match() {}

    public Match(String homeTeam, String awayTeam, String status, Instant startTime, long homeScore, long awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
        this.startTime = startTime;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
    }

    public String getHomeTeam() { 
        return homeTeam; 
    }

    public void setHomeTeam(String homeTeam) {
         this.homeTeam = homeTeam; 
    }

    public String getAwayTeam() {
         return awayTeam; 
    }

    public void setAwayTeam(String awayTeam) {
         this.awayTeam = awayTeam; 
    }

    public String getStatus() {
         return status; 
    }
    
    public void setStatus(String status) {
         this.status = status; 
    }

    public Instant getStartTime() {
     return startTime;
    }

    public void setStartTime(Instant startTime) {
     this.startTime = startTime;
    }

    public Long getHomeScore() {
     return homeScore;
    }

    public void setHomeScore(Long homeScore) {
     this.homeScore = homeScore;
    }

    public Long getAwayScore() {
     return awayScore;
    }

    public void setAwayScore(Long awayScore) {
     this.awayScore = awayScore;
    }
}
