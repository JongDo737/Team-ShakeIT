package com.example.test.controller;

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

}
