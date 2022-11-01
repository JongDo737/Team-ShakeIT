package com.example.shake.service;

import com.example.shake.dto.CongressOfMemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface APIService {
    public String  insertCongressOfMember(List<CongressOfMemberDto> members);
}
