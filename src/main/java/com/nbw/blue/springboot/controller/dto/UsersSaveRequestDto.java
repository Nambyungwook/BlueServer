package com.nbw.blue.springboot.controller.dto;

import com.nbw.blue.springboot.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
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
    private String signType;
    private String signToken;
    private Long appdoc_index;

    @Builder
    public UsersSaveRequestDto(String email,
                               String pwd,
                               String name,
                               String birthday,
                               String gender,
                               String local,
                               String job,
                               String interest,
                               Integer income,
                               String phone,
                               String signType,
                               String signToken,
                               Long appdoc_index) {
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
        this.signType = signType;
        this.signToken = signToken;
        this.appdoc_index = appdoc_index;
    }

    public Users toEntity() {
        return Users.builder()
                .email(email)
                .pwd(pwd)
                .name(name)
                .birthday(birthday)
                .gender(gender)
                .local(local)
                .job(job)
                .interest(interest)
                .income(income)
                .phone(phone)
                .signType(signType)
                .signToken(signToken)
                .appdoc_index(appdoc_index)
                .build();
    }
}
