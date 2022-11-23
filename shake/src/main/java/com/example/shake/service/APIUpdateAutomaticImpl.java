package com.example.shake.service;

import com.example.shake.api.*;
import com.example.shake.api.auto.URLConnect;
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
public class APIUpdateAutomaticImpl implements APIUpdateAutomatic {
    final BillRepository billRepository;
    final CalenderRepository calenderRepository;
    final CongressOfMemberRepository congressOfMemberRepository;
    final LegislativeStatusRepository legislativeStatusRepository;
    final PendingPetitionRepository pendingPetitionRepository;
    final ProcessedPetitionRepository processedPetitionRepository;
    final UserRepository userRepository;
    final NotificationRepository notificationRepository;

    @Override
    public String updateDataBase() throws ParserConfigurationException, IOException, SAXException {
//        // 국회의원 코드로 검색해서 있는지 검색
//        List<CongressOfMember> congressOfMemberList = MemberOfCongressAPI.getAPIList().stream()
//                .parallel()
//                .map(CongressOfMemberDto::toEntity)
//                .filter((CongressOfMember)-> !congressOfMemberRepository.existsCongressOfMemberByMONACD(CongressOfMember.getMONACD()))
//                .collect(Collectors.toList());
//        System.out.println("없는 국회의원 목록 추가");
//        congressOfMemberList.stream().forEach(System.out::println);
//        congressOfMemberRepository.saveAll(congressOfMemberList);

        //진행중 청원은 태이블 지우고 새로해야함
//        System.out.println("진행중 청원 DB 수정합니다.");
//        pendingPetitionRepository.deleteAll();
//        List<PendingPetition> pendingPetitions = PendingPetitionAPI.getAPIList().stream()
//                .parallel().map(PendingPetitionDto::toEntity).collect(Collectors.toList());
//        pendingPetitionRepository.saveAll(pendingPetitions);
//        System.out.println("진행중 청원 " + pendingPetitions.size() + "개 새로고침");
        List<PendingPetition> pendingPetitions = PendingPetitionAPI.getAPIList()
                .stream()
                .parallel()
                .map((PendingPetitionDto::toEntity))
                .filter(pendingPetition ->
                        !pendingPetitionRepository.existsPendingPetitionByBillid(pendingPetition.getBillid()))
                .collect(Collectors.toList());
        System.out.println("없는 진행중 청원 목록 추가");
        pendingPetitionRepository.saveAll(pendingPetitions);


        //캘린더
        List<Calendar> calendarList = getNotInDBCalendarList();
        calendarList.stream().forEach(System.out::println);
        calenderRepository.saveAll(calendarList);

        // 종료 입법
        List<LegislativeStatus> legislativeStatuses = LegislativeStatusAPI.getAPIList()
                .stream()
                .parallel()
                .map((LegislativeStatusDto::toEntity))
                .filter(legislativeStatus ->
                        !legislativeStatusRepository.existsLegislativeStatusByBillid(legislativeStatus.getBillid()))
                .collect(Collectors.toList());
        System.out.println("없는 종료 입법 목록 추가");
        legislativeStatuses.stream().forEach(System.out::println);
        legislativeStatusRepository.saveAll(legislativeStatuses);


        // 완료된 청원
        List<ProcessedPetition> processedPetitions = ProcessedPetitionAPI.getAPIList()
                .stream()
                .parallel()
                .map((ProcessedPetitionDto::toEntity))
                .filter(processedPetition ->
                        !processedPetitionRepository.existsProcessedPetitionByBillid(processedPetition.getBillid()))
                .collect(Collectors.toList());
        System.out.println("없는 완료된 청원 목록 추가");
        processedPetitions.stream().forEach(System.out::println);
        processedPetitionRepository.saveAll(processedPetitions);

        System.out.println("새로고침 완료");

        // 새로 생긴 데이터가 존재할 때 공지사항 DB에 저장
        if(calendarList.size() != 0 || pendingPetitions.size() != 0 || processedPetitions.size() != 0 || legislativeStatuses.size() != 0){
            List<Notification> notiList =  calendarList.stream().map(Calendar::toNotification).collect(Collectors.toList());
            notiList.addAll(pendingPetitions.stream().map(PendingPetition::toNotification).collect(Collectors.toList()));
            notiList.addAll(processedPetitions.stream().map(ProcessedPetition::toNotification).collect(Collectors.toList()));
            notiList.addAll(legislativeStatuses.stream().map(LegislativeStatus::toNotification).collect(Collectors.toList()));
            System.out.println("공지사항 추가");
            notiList.stream().forEach(System.out::println);
            notificationRepository.saveAll(notiList);
        }

        return "헷";
    }

    public List<Bill> getNotInDBBillList() throws ParserConfigurationException, IOException, SAXException {
// 입법 bill
        List<Bill> billList = BillAPI.getAPIList().stream()
                .parallel()
                .map(BillDto::toEntity)
                .filter((Bill) -> !billRepository.existsBillByBillid(Bill.getBillid()))
                .collect(Collectors.toList());
        System.out.println("없는 입법 목록 추가");
        billList.stream().forEach(System.out::println);
        billRepository.saveAll(billList);
        // 1개 이상이면 푸쉬 메시지 전송
        if (billList.size() > 0) {
            List<Notification> notiList =  billList.stream().map(Bill::toNotification).collect(Collectors.toList());
            notificationRepository.saveAll(notiList);
            List<User> userList = userRepository.findAll();
            userList.stream().parallel().forEach((user) -> {
                try {
                    URLConnect.sendMessage(user.getToken(), "새로운 입법 정보가 도착했어요.", billList.get(0).getBill_name() + " 외 " + (billList.size() - 1) + "건");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return billList;
    }

    @Override
    public List<Calendar> getNotInDBCalendarList() throws ParserConfigurationException, IOException, SAXException {
        // 1 : 국회의원 세미나
        // 2 : 본회의 일정
        // 3 : 위원회 별 일정
        // 4 : 국회의장 일정
        // 5 : 공청회 일정

        List<Calendar> calendarList = CalenderBon.getAPIList()
                .stream()
                .parallel()
                .map(CalendarDto::toEntity)
                .filter((Calendar) -> !calenderRepository.existsCalendarByUrl(Calendar.getUrl()))
                .collect(Collectors.toList());

        CalenderSemina.getAPIList()
                .stream()
                .parallel()
                .map(CalendarDto::toEntity)
                .filter((Calendar) -> !calenderRepository.existsCalendarByUrl(Calendar.getUrl()))
                .forEach((Calendar) -> calendarList.add(Calendar));
        CalenderWee.getAPIList()
                .stream()
                .map(CalendarDto::toEntity)
                .filter((Calendar) -> !calenderRepository.existsCalendarByUrl(Calendar.getUrl()))
                .forEach((Calendar) -> calendarList.add(Calendar));
        CalenderGook.getAPIList()
                .stream()
                .parallel()
                .map(CalendarDto::toEntity)
                .filter((Calendar) -> !calenderRepository.existsCalendarByTitle(Calendar.getTitle()))
                .forEach((Calendar) -> calendarList.add(Calendar));
        CalenderGong.getAPIList()
                .stream()
                .parallel()
                .map(CalendarDto::toEntity)
                .filter((Calendar) -> !calenderRepository.existsCalendarByUrl(Calendar.getUrl()))
                .forEach((Calendar) -> calendarList.add(Calendar));
        CalenderGong.getAPIList()
                .stream()
                .parallel()
                .map(CalendarDto::toEntity)
                .forEach(calendarList::add);
        System.out.println("없는 캘린더 목록 추가");
        return calendarList;

    }
}
