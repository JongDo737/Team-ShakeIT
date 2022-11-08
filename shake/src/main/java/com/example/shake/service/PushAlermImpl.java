package com.example.shake.service;

import com.example.shake.api.Date;
import com.example.shake.entity.User;
import com.example.shake.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushAlermImpl implements PushAlerm{
    final UserRepository userRepository;
    @Override
    public void insertUserToken(String token) {
        // 토큰 정보가 없다면
        if(!userRepository.existsByToken(token)){
            User user = new User();
            user.setToken(token);
            user.setCreatedate(Date.getDate());
            userRepository.save(user);
        }

    }
}
