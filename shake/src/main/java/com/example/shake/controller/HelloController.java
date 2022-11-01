package com.example.shake.controller;

import com.example.shake.api.IndexAPI;
import com.example.shake.entity.TestEntity;
import com.example.shake.repository.TestRepository;
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

        System.out.println(123);
        IndexAPI.getAPIList();
        return testRepository.findAll();
    }
    @GetMapping("/insertMember")
    public String insertMember(){

        return "성공";
    }
}
