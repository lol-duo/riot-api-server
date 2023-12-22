package com.lolduo.riotapiserver.controller;

import com.lolduo.riotapiserver.Service;
import com.lolduo.riotapiserver.record.DetailLeagueRecord;
import com.lolduo.riotapiserver.record.LeagueRecord;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;

@RequestMapping("/league")
@RestController
@AllArgsConstructor()
public class LeagueController {

    private Service leagueService;

    @GetMapping("/challenger")
    public LeagueRecord challenger() {
        URI uri = URI.create("https://kr.api.riotgames.com/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5");
        return leagueService.getApi(LeagueRecord.class, uri);
    }

    @GetMapping("/grandmaster")
    public LeagueRecord grandmaster() {
        URI uri = URI.create("https://kr.api.riotgames.com/lol/league/v4/grandmasterleagues/by-queue/RANKED_SOLO_5x5");
        return leagueService.getApi(LeagueRecord.class,uri);
    }

    @GetMapping("/master")
    public LeagueRecord master() {
        URI uri = URI.create("https://kr.api.riotgames.com/lol/league/v4/masterleagues/by-queue/RANKED_SOLO_5x5");
        return leagueService.getApi(LeagueRecord.class,uri);
    }

    @GetMapping("/{tier}/{division}/{page}")
    public DetailLeagueRecord[] tierDivisionPage(@PathVariable String tier, @PathVariable String division, @PathVariable int page) {
        String sb = "/lol/league/v4/entries/RANKED_SOLO_5x5/" +
                tier +
                "/" +
                division +
                "?page=" +
                page;

        URI uri = URI.create("https://kr.api.riotgames.com" + sb);

        return leagueService.getApi(DetailLeagueRecord[].class, uri);
    }
}
