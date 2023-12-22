package com.lolduo.riotapiserver.controller;

import com.lolduo.riotapiserver.Service;
import com.lolduo.riotapiserver.record.SummonerRecord;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/summoner")
public class SummonerController {

    public Service service;

    @GetMapping("/{summonerId}")
    public SummonerRecord bySummonerId(@PathVariable String summonerId) {
        URI uri = URI.create("https://kr.api.riotgames.com/lol/summoner/v4/summoners/" + summonerId);

        return service.getApi(SummonerRecord.class, uri);
    }
}
