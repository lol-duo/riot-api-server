package com.lolduo.riotapiserver.controller;

import com.lolduo.riotapiserver.Service;
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

    @GetMapping("/apiCallCount")
    public int apiCallCount() {
       return service.getApiCallCount();
   }

    @GetMapping("/apiCallCountReset")
    public void apiCallCountReset() {
        service.resetApiCallCount();
    }
}
