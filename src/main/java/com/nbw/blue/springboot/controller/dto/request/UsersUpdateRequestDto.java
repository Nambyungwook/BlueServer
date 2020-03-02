package com.nbw.blue.springboot.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
    private Long id;
    private String email;
    private String pwd;
    private String name;
    private String birthday;
    private String gender;
    private String local;
    private String job;
    private String interest;
    private Integer income;
    private String phone;
    private Long appdoc_index;

    @Builder
    public UsersUpdateRequestDto(Long id,
                                 String email,
                                 String pwd,
                                 String name,
                                 String birthday,
                                 String gender,
                                 String local,
                                 String job,
                                 String interest,
                                 Integer income,
                                 String phone,
                                 Long appdoc_index) {
        this.id = id;
        this.email = email;
        this.pwd = pwd;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.local = local;
        this.job = job;
        this.interest = interest;
        this.income = income;
        this.phone = phone;
        this.appdoc_index = appdoc_index;
    }
}