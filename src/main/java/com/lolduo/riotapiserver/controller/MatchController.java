package com.lolduo.riotapiserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/matches")
public class MatchController {

    @GetMapping("/by-puuid/:puuid")
    public String byPuuid() {
        return "Hello World!";
    }

    @GetMapping("/:matchId")
    public String byMatchId() {
        return "Hello World!";
    }

    @GetMapping("/:matchId/timeline")
    public String byMatchIdTimeline() {
        return "Hello World!";
    }
}
