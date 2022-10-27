package com.example.test.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping("/test")
    public String printHello() throws Exception {
//        IndexAPI indexAPI = new IndexAPI();
//        indexAPI.getIndexApi3();
        return "Hello";
    }

}
