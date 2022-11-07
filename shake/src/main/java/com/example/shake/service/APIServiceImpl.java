package com.example.shake.service;

import com.example.shake.api.*;
import com.example.shake.dto.*;
import com.example.shake.entity.*;
import com.example.shake.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {
    final CongressOfMemberRepository congressOfMemberRepository;
    final CalenderRepository calenderRepository;
    final PendingPetitionRepository pendingPetitionRepository;
    final ProcessedPetitionRepository processedPetitionRepository;
    final LegislativeStatusRepository legislativeStatusRepository;
    final BillRepository billRepository;
    @Override
    public String insertCongressOfMember(List<CongressOfMemberDto> members) {

//        for (int i = 0; i < members.size(); i++) {
//            members.get(i).setId(Long.parseLong(i+""));
//            members.get(i).setCreate_date(Date.getDate());
//            members.get(i).setUpdate_date(Date.getDate());
//            CongressOfMember congressOfMember = members.get(i).toEntity();
//            congressOfMemberRepository.save(congressOfMember);
//            System.out.println(congressOfMember.getHG_NM());
//        }
        members.stream().parallel().map(CongressOfMemberDto::toEntity)
                .forEach((CongressOfMember)-> congressOfMemberRepository.save(CongressOfMember));


        return "국회의원 데이터 넣기 성공";
    }

    @Override
    public String insertCalenderDate() throws SAXException, ParserConfigurationException, IOException {
        // 데이터 넣기 람다 사용
        // 병렬화 사용 List 라서 효율 좋음 !
        // 출력
//        List<CalendarDto> calendarDtos = CalenderSemina.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderBon.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderWee.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGook.getAPIList();
//        List<CalendarDto> calendarDtos = CalenderGong.getAPIList();
//        calendarDtos.stream().forEach(System.out::println);

        // 국회 세미나 데이터
        List<Calendar> calendarList = CalenderSemina.getAPIList().stream().parallel()
                .map(CalendarDto::toEntity).collect(Collectors.toList());
        System.out.println("국회 세미나 데이터 #####################");
        calendarList.stream().forEach(System.out::println);
        calenderRepository.saveAll(calendarList);
        // 본회의 데이터
        calendarList = CalenderBon.getAPIList().stream().parallel()
                .map(CalendarDto::toEntity).collect(Collectors.toList());
        System.out.println("본회의 데이터 #####################");
        calendarList.stream().forEach(System.out::println);
        calenderRepository.saveAll(calendarList);
        // 위원회 데이터
        calendarList = CalenderWee.getAPIList().stream().parallel()
                .map(CalendarDto::toEntity).collect(Collectors.toList());
        System.out.println("위원회 데이터 #####################");
        calendarList.stream().forEach(System.out::println);
        calenderRepository.saveAll(calendarList);
        // 국회의장 데이터
        calendarList = CalenderGook.getAPIList().stream().parallel()
                .map(CalendarDto::toEntity).collect(Collectors.toList());
        System.out.println("국회의장 데이터 #####################");
        calendarList.stream().forEach(System.out::println);
        calenderRepository.saveAll(calendarList);
        // 공청회 데이터
        calendarList = CalenderGong.getAPIList().stream().parallel()
                .map(CalendarDto::toEntity).collect(Collectors.toList());
        System.out.println("공청회 데이터 #####################");
        calendarList.stream().forEach(System.out::println);
        calenderRepository.saveAll(calendarList);

        return "캘린더 데이터 넣기 성공";
    }

    @Override
    public String insertPendingPetitions() throws ParserConfigurationException, SAXException, IOException {
//        PendingPetitionAPI.getAPIList().stream().map(PendingPetitionDto::toEntity).map(pendingPetitionRepository::save);
        List<PendingPetition> pendingPetitions = PendingPetitionAPI.getAPIList().stream().map(PendingPetitionDto::toEntity).collect(Collectors.toList());
        pendingPetitionRepository.saveAll(pendingPetitions);
        return "진행중인 청원 데이터 넣기 성공";
    }

    @Override
    public String insertProcessedPetitions() throws ParserConfigurationException, SAXException, IOException {
        ProcessedPetitionAPI.getAPIList().stream().parallel().map(ProcessedPetitionDto::toEntity)
                .forEach((ProcessedPetition) -> processedPetitionRepository.save(ProcessedPetition));
        return "만료된 청원 데이터 넣기 성공";
    }

    @Override
    public String insertLegislativeStatus() throws ParserConfigurationException, SAXException, IOException {
        LegislativeStatusAPI.getAPIList().parallelStream().map(LegislativeStatusDto::toEntity)
                .forEach((LegislativeStatus) -> legislativeStatusRepository.save(LegislativeStatus));
        return "진행중 입법예고 데이터 넣기 성공";
    }

    @Override
    public String insertBill() throws ParserConfigurationException, SAXException, IOException {
        BillAPI.getAPIList().stream().parallel().map(BillDto::toEntity).distinct().forEach((Bill) -> billRepository.save(Bill));
        return "본회의 처리안 데이터 넣기 성공";
    }

    @Override
    public List<Bill> getBillNotAnnounceDt() {
        List<Bill> bills = billRepository.findAll();
        bills = bills.stream()
                .parallel()
                .filter((bill) -> bill.getAnnounce_dt() != null)
                .collect(Collectors.toList());
        return bills;
    }
}
