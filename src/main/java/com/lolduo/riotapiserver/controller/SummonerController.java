package com.lolduo.riotapiserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/summoner")
public class SummonerController {

    @GetMapping("/by-name/:summonerName")
    public String byName() {
        return "Hello World!";
    }

    @GetMapping("/:summonerId")
    public String bySummonerId() {
        return "Hello World!";
    }
}
