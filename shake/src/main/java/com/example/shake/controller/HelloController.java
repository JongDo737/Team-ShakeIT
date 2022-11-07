package com.example.shake.controller;

import com.example.shake.api.*;
import com.example.shake.api.auto.Scheduler;
import com.example.shake.api.auto.URLConnect;
import com.example.shake.dto.CalendarDto;
import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.dto.LegislativeStatusDto;
import com.example.shake.dto.PendingPetitionDto;
import com.example.shake.entity.*;
import com.example.shake.repository.*;
import com.example.shake.service.APIService;
import com.example.shake.service.APIUpdateAutomatic;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
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
    final APIUpdateAutomatic apiUpdateAutomatic;
    final CongressOfMemberRepository congressOfMemberRepository;
    final CalenderRepository calenderRepository;
    final PendingPetitionRepository pendingPetitionRepository;
    final ProcessedPetitionRepository processedPetitionRepository;
    final LegislativeStatusRepository legislativeStatusRepository;
    final BillRepository billRepository;
    // CongressMember ##############################################
    @GetMapping("/insertCongressMemberencodemeomd")
    public String insertMember() throws ParserConfigurationException, IOException, SAXException {
        List<CongressOfMemberDto> congressOfMemberDtos = MemberOfCongressAPI.getAPIList();
        return apiService.insertCongressOfMember(congressOfMemberDtos);
    }

    @RequestMapping(value = "/getCongressMember", produces = "application/json; charset=utf8")
    public List<CongressOfMember> getMember()  {
        return congressOfMemberRepository.findAll();
    }
    // CongressMember ##############################################
    // Calendar ##############################################
    @RequestMapping(value = "/getCalendarBon", produces = "application/json; charset=utf8")
    public void getCalendarBon() throws ParserConfigurationException, IOException, SAXException {

//        List<CalendarDto> calendarDtos = CalenderSemina.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderBon.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderWee.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGook.getAPIList();
        List<CalendarDto> calendarDtos = CalenderGong.getAPIList();

        calendarDtos.stream().forEach(System.out::println);

    }
    @RequestMapping(value = "/insertCalendar2y344t56uy55ergr4u5y", produces = "application/json; charset=utf8")
    public String insertCalendar() throws ParserConfigurationException, IOException, SAXException {
        return apiService.insertCalenderDate();
    }
    @RequestMapping(value = "/getCalendar", produces = "application/json; charset=utf8")
    public List<Calendar> getCalendar() throws ParserConfigurationException, IOException, SAXException {
        return calenderRepository.findAll(Sort.by(Sort.Direction.DESC, "date"));
    }
    @RequestMapping(value = "/getCalendarByCode", produces = "application/json; charset=utf8")
    public List<Calendar> getCalendarByCode() throws ParserConfigurationException, IOException, SAXException {
        return calenderRepository.findAll(Sort.by(Sort.Direction.ASC, "code"));
    }
    // PendingPetition ##############################################
    @GetMapping("/getPendingPetition")
    public List<PendingPetition> getPetitions() throws ParserConfigurationException, IOException, SAXException {
        return pendingPetitionRepository.findAll();
    }
    @GetMapping("/insertPendingPetition45725453")
    public String insertPendingPetitions() throws ParserConfigurationException, IOException, SAXException {
        return apiService.insertPendingPetitions();
    }
    // ProcessedPetition ##############################################
    @GetMapping("/getProcessedPetition")
    public List<ProcessedPetition> getProcessedPetitions() throws ParserConfigurationException, IOException, SAXException {
        return processedPetitionRepository.findAll();
    }
    @GetMapping("/insertProcessedPetition45725453")
    public String insertProcessedPetitions() throws ParserConfigurationException, IOException, SAXException {
        return apiService.insertProcessedPetitions();
    }
    // LegislativeStatus ##############################################
    @GetMapping("/getLegislativeStatus")
    public List<LegislativeStatus> getLegislativeStatus() throws ParserConfigurationException, IOException, SAXException {
        return legislativeStatusRepository.findAll();
    }
    @GetMapping("/insertLegislativeStatus45725453")
    public String insertLegislativeStatus() throws ParserConfigurationException, IOException, SAXException {
        return apiService.insertLegislativeStatus();
    }

    // Bill ##############################################
    @GetMapping("/getBill")
    public List<Bill> getBill() throws ParserConfigurationException, IOException, SAXException {
        return apiService.getBillNotAnnounceDt();
    }
    @GetMapping("/insertBill45725453")
    public String insertBill() throws ParserConfigurationException, IOException, SAXException {
        return apiService.insertBill();
    }

    @GetMapping("/updateDB")
    public String updateData() throws ParserConfigurationException, IOException, SAXException {
        return apiUpdateAutomatic.updateDataBase();
    }

    @GetMapping("/getMessageList")
    public List<Bill> message() throws ParserConfigurationException, IOException, SAXException {
        return apiUpdateAutomatic.getNotInDBBillList();
    }

    @GetMapping("/startThread")
    public void thread() throws IOException {
        Scheduler scheduler = new Scheduler();
        System.out.println("스케줄러 생성 !!");
        scheduler.execute(() -> {
            try {
                URLConnect.go();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        },0,0,1);

    }


}
