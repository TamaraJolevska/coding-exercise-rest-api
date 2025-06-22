package com.codingexercise.restapi.controller;

import com.codingexercise.restapi.service.MatchService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/matches")
public class MatchController {

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
}
