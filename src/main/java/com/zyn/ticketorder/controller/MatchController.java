package com.zyn.ticketorder.controller;

import com.zyn.ticketorder.model.Match;
import com.zyn.ticketorder.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // 获取所有比赛
    @GetMapping("/getallmatches")
    public List<Match> getAllMatches() {
        return matchService.getAllMatches();
    }
}
