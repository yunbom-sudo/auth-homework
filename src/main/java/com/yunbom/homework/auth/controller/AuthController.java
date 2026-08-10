package com.yunbom.homework.auth.controller;

import com.yunbom.homework.auth.dto.request.SignupRequest;
import com.yunbom.homework.auth.dto.response.SignupResponse;
import com.yunbom.homework.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupRequest request){
       return authService.signup(request);
    }


}
