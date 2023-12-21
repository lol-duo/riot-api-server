package com.lolduo.riotapiserver.controller;

import com.lolduo.riotapiserver.Service;
import com.lolduo.riotapiserver.record.LeagueRecord;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Controller {

    public Service service;

    @GetMapping("/")
    public String index() {
        return "Hello World!";
    }

//    @GetMapping("/apiCallCount")
//    public int apiCallCount() {
//        return service.getApiCallCount();
//    }

    @GetMapping("/errorCount")
    public int errorCount() {
        return service.getErrorCount();
    }
    @GetMapping("/test")
    public void test33() {
       service.test();
    }
    @GetMapping("/readTest")
    public void test22() {
        service.readTest();
    }

    @GetMapping("/resetApiCallCount")
    public String resetApiCallCount() {
        return "Hello World!";
    }
}
