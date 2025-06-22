package com.codingexercise.restapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {
    @JsonProperty("status")
    private String status;
    @JsonProperty("home_team")
    private String homeTeam;
    @JsonProperty("away_team")
    private String awayTeam;

    public Match() {}

    public Match(String homeTeam, String awayTeam, String status) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.status = status;
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
}
