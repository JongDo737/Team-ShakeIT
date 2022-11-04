package com.example.shake.controller;

import com.example.shake.api.*;
import com.example.shake.dto.CalendarDto;
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
        return Date.getDate();
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

    @RequestMapping(value = "/getCalendarBon", produces = "application/json; charset=utf8")
    public void getCalendarBon() throws ParserConfigurationException, IOException, SAXException {

//        List<CalendarDto> calendarDtos = CalenderSemina.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderBon.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderWee.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGook.getAPIList();
        List<CalendarDto> calendarDtos = CalenderGong.getAPIList();

        calendarDtos.stream().forEach(System.out::println);

    }
    @RequestMapping(value = "/insertCalendar", produces = "application/json; charset=utf8")
    public void insertCalendar() throws ParserConfigurationException, IOException, SAXException {

//        List<CalendarDto> calendarDtos = CalenderSemina.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderBon.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderWee.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGook.getAPIList();
        List<CalendarDto> calendarDtos = CalenderGong.getAPIList();

        calendarDtos.stream().forEach(System.out::println);

    }

}
