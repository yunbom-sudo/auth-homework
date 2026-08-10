package com.yunbom.homework.auth.controller;

import com.yunbom.homework.auth.dto.request.SignupRequest;
import com.yunbom.homework.auth.entity.UserEntity;
import com.yunbom.homework.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequest request){
        authService.signup(request);
    }


}
