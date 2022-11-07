package com.example.shake.service;

import com.example.shake.api.MemberOfCongressAPI;
import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.entity.CongressOfMember;
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
public class APIUpdateAutomaticImpl implements APIUpdateAutomatic{
    final BillRepository billRepository;
    final CalenderRepository calenderRepository;
    final CongressOfMemberRepository congressOfMemberRepository;
    final LegislativeStatusRepository legislativeStatusRepository;
    final PendingPetitionRepository pendingPetitionRepository;
    final ProcessedPetitionRepository processedPetitionRepository;
    @Override
    public String updateDataBase() throws ParserConfigurationException, IOException, SAXException {
        // 국회의원
        List<CongressOfMember> congressOfMemberList = MemberOfCongressAPI.getAPIList().stream()
                .parallel()
                .map(CongressOfMemberDto::toEntity)
                .filter((CongressOfMember)-> !congressOfMemberRepository.findAll().contains(CongressOfMember))
                .collect(Collectors.toList());
        congressOfMemberList.stream().forEach(System.out::println);


        return null;
    }
}
