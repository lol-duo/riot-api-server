package com.lolduo.riotapiserver.controller;

import com.lolduo.riotapiserver.Service;
import com.lolduo.riotapiserver.record.MatchRecord;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/matches")
public class MatchController {

    public Service service;

    @GetMapping("/by-puuid/{puuid}")
    public String[] byPuuid(@PathVariable String puuid) {
        URI uri = URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=100");
        return service.getApi(String[].class, uri);
    }

    @GetMapping("/{matchId}")
    public MatchRecord byMatchId(@PathVariable String matchId) {
        URI uri = URI.create("https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId);
        return service.getApi(MatchRecord.class, uri);
    }
}
