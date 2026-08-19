package com.yunbom.homework.auth.dto.response;

import com.yunbom.homework.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    private Long userId;

    private String email;

    private Role role;

}
