package com.zyn.ticketorder.service;

import com.zyn.ticketorder.model.Match;
import com.zyn.ticketorder.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    // 获取所有比赛
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
