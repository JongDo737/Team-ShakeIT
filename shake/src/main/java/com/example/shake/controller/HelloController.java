package com.example.shake.controller;

import com.example.shake.api.*;
import com.example.shake.api.auto.Scheduler;
import com.example.shake.api.auto.URLConnect;
import com.example.shake.api.firebase.RequestDTO;
import com.example.shake.dto.CalendarDto;
import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.entity.*;
import com.example.shake.api.firebase.FirebaseCloudMessageService;
import com.example.shake.repository.*;
import com.example.shake.service.APIService;
import com.example.shake.service.APIUpdateAutomatic;
import com.example.shake.service.PushAlerm;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
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
    final FirebaseCloudMessageService firebaseCloudMessageService;
    final PushAlerm pushAlerm;

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
    public String thread() throws IOException {
        Scheduler scheduler = new Scheduler();
        System.out.println("스케줄러 생성 !!");
        scheduler.execute(() -> {
            try {
                URLConnect.go();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        },10,0,0);
        return "DB자동 수정기능 ON";
    }

    @PostMapping("/api/fcm/")
    public ResponseEntity pushMessage(@RequestBody RequestDTO requestDTO) throws IOException {
        System.out.println(requestDTO.getTargetToken() + " "
                +requestDTO.getTitle() + " " + requestDTO.getBody());

        firebaseCloudMessageService.sendMessageTo(
                requestDTO.getTargetToken(),
                requestDTO.getTitle(),
                requestDTO.getBody());
        return ResponseEntity.ok().build();
    }
    @GetMapping("/sendPushMsg/{token}/{title}/{body}")
    public ResponseEntity sendMsg(@PathVariable("token") String token,@PathVariable("title") String title, @PathVariable("body") String body) throws IOException {
        firebaseCloudMessageService.sendMessageTo(
                token,
                title,
                body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/push/{tokens}")
    public List<String> insertToken(@PathVariable("tokens") String tokens) {
        System.out.println(tokens);
        pushAlerm.insertUserToken(tokens);
        return List.of("토큰받기 성공");
    }

}
