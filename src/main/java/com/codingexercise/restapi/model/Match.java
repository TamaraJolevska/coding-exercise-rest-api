package com.codingexercise.restapi.model;

public class Match {
    private String status;
    private String homeTeam;
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
