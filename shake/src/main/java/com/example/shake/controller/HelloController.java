package com.example.shake.controller;

import com.example.shake.api.Calendar;
import com.example.shake.api.IndexAPI;
import com.example.shake.api.MemberOfCongressAPI;
import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.entity.CongressOfMember;
import com.example.shake.entity.TestEntity;
import com.example.shake.repository.CongressOfMemberRepository;
import com.example.shake.repository.TestRepository;
import com.example.shake.service.APIService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.lang.reflect.Member;
import java.util.List;

@RestController
@AllArgsConstructor
public class HelloController {
    final TestRepository testRepository;
    final APIService apiService;
    final CongressOfMemberRepository congressOfMemberRepository;

    @GetMapping("/test")
    public String printHello() throws Exception {
        IndexAPI.getAPIList();
        return Calendar.getDate();
    }
    @GetMapping("/api")
    public List<TestEntity> printAPI() throws Exception {
        return testRepository.findAll();
    }
    @GetMapping("/insertCongressMemberencodemeomd")
    public String insertMember() throws ParserConfigurationException, IOException, SAXException {
        List<CongressOfMemberDto> congressOfMemberDtos = MemberOfCongressAPI.getAPIList();
        return apiService.insertCongressOfMember(congressOfMemberDtos);
    }

    @RequestMapping(value = "/getCongressMember", produces = "application/json; charset=utf8")
    public List<CongressOfMember> getMember()  {
        return congressOfMemberRepository.findAll();
    }
}
