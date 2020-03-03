package com.nbw.blue.springboot.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersSigninRequestDto {
    private String email;
    private String pwd;
}
