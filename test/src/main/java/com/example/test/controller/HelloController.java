package com.example.test.controller;

import com.example.test.api.IndexAPI;
import com.example.test.api.MemberOfCongressAPI;
import com.example.test.entity.TestEntity;
import com.example.test.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class HelloController {
    final TestRepository testRepository;

    @GetMapping("/test")
    public List<TestEntity> printHello() throws Exception {
        return testRepository.findAll();
    }
    @GetMapping("/api")
    public List<TestEntity> printAPI() throws Exception {
//        MemberOfCongressAPI.getAPIList();
        IndexAPI.getAPIList();
        return testRepository.findAll();
    }

}
