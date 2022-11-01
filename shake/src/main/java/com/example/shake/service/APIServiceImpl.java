package com.example.shake.service;

import com.example.shake.api.Calendar;
import com.example.shake.api.MemberOfCongressAPI;
import com.example.shake.dto.CongressOfMemberDto;
import com.example.shake.entity.CongressOfMember;
import com.example.shake.repository.CongressOfMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {
    final CongressOfMemberRepository congressOfMemberRepository;

    @Override
    public String insertCongressOfMember(List<CongressOfMemberDto> members) {

        for (int i = 0; i < members.size(); i++) {
            members.get(i).setId(Long.parseLong(i+""));
            members.get(i).setCreate_date(Calendar.getDate());
            members.get(i).setUpdate_date(Calendar.getDate());
            CongressOfMember congressOfMember = members.get(i).toEntity();
            congressOfMemberRepository.save(congressOfMember);
            System.out.println(congressOfMember.getHG_NM());
        }

        return "국회의원 데이터 넣기 성공";
    }
}
