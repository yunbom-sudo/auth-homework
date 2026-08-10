package com.yunbom.homework.auth.service;

import com.yunbom.homework.auth.dto.request.SignupRequest;
import com.yunbom.homework.auth.entity.Role;
import com.yunbom.homework.auth.entity.UserEntity;
import com.yunbom.homework.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void signup(SignupRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
          throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .build();

        userRepository.save(userEntity);
    }
}
