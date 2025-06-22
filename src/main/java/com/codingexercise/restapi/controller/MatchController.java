package com.codingexercise.restapi.controller;

import com.codingexercise.restapi.model.Match;
import com.codingexercise.restapi.service.MatchService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public String allMatches(Model model) {
        model.addAttribute("matches", matchService.getAllMatches());
        return "matches";
    }

    @GetMapping("/live")
    public String liveMatches(Model model) {
        model.addAttribute("matches", matchService.getMatchesByStatus("LIVE"));
        return "matches";
    }

    @GetMapping("/completed")
    public String completedMatches(Model model) {
        model.addAttribute("matches", matchService.getMatchesByStatus("COMPLETED"));
        return "matches";
    }

    @GetMapping("/filter")
    public String filterMatchesByTeam(@RequestParam(value = "teamName", required = false) String teamName, Model model) {
        List<Match> matches = new ArrayList<>();

        if (teamName != null && !teamName.isEmpty()) {
            matches.addAll(matchService.getMatchesByTeam(teamName));
        }

        model.addAttribute("matches", matches);
        model.addAttribute("teamName", teamName);
    
        return "matches";
    }
}
