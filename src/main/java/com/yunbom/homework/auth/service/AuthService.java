package com.yunbom.homework.auth.service;

import com.yunbom.homework.auth.dto.request.LoginRequest;
import com.yunbom.homework.auth.dto.request.SignupRequest;
import com.yunbom.homework.auth.dto.response.SignupResponse;
import com.yunbom.homework.auth.dto.response.TokenResponse;
import com.yunbom.homework.auth.entity.Role;
import com.yunbom.homework.auth.entity.UserEntity;
import com.yunbom.homework.auth.repository.UserRepository;
import com.yunbom.homework.auth.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    @Transactional
    public SignupResponse signup(SignupRequest request){

        if(userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        UserEntity userEntity = UserEntity.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .role(Role.USER)
                .build();

        UserEntity savedUser = userRepository.save(userEntity);

        SignupResponse signupResponse = new SignupResponse(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getRole()
        );

        return signupResponse;
    }

    @Transactional(readOnly = true)
    public TokenResponse login(LoginRequest request){
        UserEntity user = userRepository.findByEmail(request.getEmail())
                 .orElseThrow(() -> new RuntimeException("해당 이메일은 존재하지 않습니다. 회원가입을 먼저 해주세요."));

        boolean passMatch  = passwordEncoder.matches(request.getPassword(),user.getPassword());

        if(!passMatch){
            throw new RuntimeException("비밀번호가 틀렸습니다.");
        }

        String token = jwtProvider.createToken(user);

        return new TokenResponse(token);
    }

}
