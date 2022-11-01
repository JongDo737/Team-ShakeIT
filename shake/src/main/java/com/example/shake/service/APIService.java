package com.example.shake.service;

import com.example.shake.dto.CongressOfMemberDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface APIService {
    public void insertCongressOfMember(List<CongressOfMemberDto> members);
}
