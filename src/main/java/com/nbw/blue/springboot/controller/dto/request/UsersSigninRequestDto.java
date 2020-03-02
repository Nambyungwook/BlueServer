package com.nbw.blue.springboot.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSigninRequestDto {
    private String email;
    private String pwd;

    @Builder
    public UsersSigninRequestDto(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }
}
