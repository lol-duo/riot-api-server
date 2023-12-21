package com.lolduo.riotapiserver.controller;

import com.lolduo.riotapiserver.Service;
import com.lolduo.riotapiserver.record.LeagueRecord;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RequestMapping("/league")
@RestController
@AllArgsConstructor()
public class LeagueController {

    private Service leagueService;

    @GetMapping("/challenger")
    public LeagueRecord challenger() {
        //leagueService.test();
        return leagueService.getApi(LeagueRecord.class, "/lol/league/v4/challengerleagues/by-queue/RANKED_SOLO_5x5");
    }

    @GetMapping("/grandmaster")
    public LeagueRecord grandmaster() {
        return leagueService.getApi(LeagueRecord.class,"/lol/league/v4/grandmasterleagues/by-queue/RANKED_SOLO_5x5");
    }

    @GetMapping("/master")
    public LeagueRecord master() {
        return leagueService.getApi(LeagueRecord.class,"/lol/league/v4/masterleagues/by-queue/RANKED_SOLO_5x5");
    }

    @GetMapping("/{tier}/{division}/{page}")
    public LeagueRecord tierDivisionPage(@PathVariable String tier, @PathVariable String division, @PathVariable int page) {
        StringBuilder sb = new StringBuilder("lol/league/v4/entries/RANKED_SOLO_5x5/")
                .append(tier)
                .append("/")
                .append(division)
                .append("?page=")
                .append(page);
        return leagueService.getApi(LeagueRecord.class,sb.toString() );
    }
}
