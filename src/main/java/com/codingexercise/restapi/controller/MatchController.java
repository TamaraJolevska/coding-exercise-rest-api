package com.codingexercise.restapi.controller;

import com.codingexercise.restapi.model.Match;
import com.codingexercise.restapi.service.MatchService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matches")
@Tag(name = "Matches RESTApi", description = "Retrieve and filter matches")
public class MatchController {

    @Autowired
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    //This is done to show the user only the JSON result, not the full HTML code
    @Operation(summary = "Retrieves all matches", description = "Retrieves a list of all matches")
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Match>> getAllMatchesJson() {
        List<Match> allMatches = matchService.getAllMatches();
        return new ResponseEntity<>(allMatches, HttpStatus.OK);
    }

    //This method can be removed if React is used for frontend
    @GetMapping
    public String allMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());
        return "matches";
    }

    @Operation(summary = "Retrieves all live matches", description = "Retrieves matches with live status")
    @ResponseBody
    @GetMapping(value = "/live", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<Match>> getAllLiveMatchesJson() {
        List<Match> liveMatches = matchService.getMatchesByStatus("LIVE");
        return new ResponseEntity<>(liveMatches, HttpStatus.OK);
    }

    @GetMapping("/live")
    public String liveMatches(Model model) {
        model.addAttribute("matches", matchService.getMatchesByStatus("LIVE"));
        return "matches";
    }

    @Operation(summary = "Retrieves all completed matches", description = "Retrieves matches with completed status")
    @ResponseBody
    @GetMapping(value = "/completed", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<Match>> getAllCompletedMatchesJson() {
        List<Match> completedMatches = matchService.getMatchesByStatus("COMPLETED");
        return new ResponseEntity<>(completedMatches, HttpStatus.OK);
    }

    @GetMapping("/completed")
    public String completedMatches(Model model) {
        model.addAttribute("matches", matchService.getMatchesByStatus("COMPLETED"));
        return "matches";
    }

    @Operation(summary = "Filters matches by team name", description = "Filters matches by home team or away team name")
    @ResponseBody
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE) 
    public ResponseEntity<List<Match>> filterMatchesByTeamJson(@RequestParam(value = "teamName", required = false) String teamName) {
        List<Match> filteredMatches = new ArrayList<>();

        if (teamName != null && !teamName.isEmpty()) {
            filteredMatches.addAll(matchService.getMatchesByTeam(teamName));
        } else {
            filteredMatches.addAll(matchService.getAllMatches());
        }
        return new ResponseEntity<>(filteredMatches, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public String filterMatchesByTeam(@RequestParam(value = "teamName", required = false) String teamName, Model model) {
        List<Match> matches = new ArrayList<>();

        if (teamName != null && !teamName.isEmpty()) {
            matches.addAll(matchService.getMatchesByTeam(teamName));
        } else {
            matches.addAll(matchService.getAllMatches());
        }

        model.addAttribute("matches", matches);
        model.addAttribute("teamName", teamName);
    
        return "matches";
    }
}
