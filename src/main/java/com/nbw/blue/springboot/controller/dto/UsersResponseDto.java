package com.nbw.blue.springboot.controller.dto;

import com.nbw.blue.springboot.domain.users.Users;
import lombok.Getter;

@Getter
public class UsersResponseDto {

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
    private String signType;
    private String signToken;
    private Long appdoc_index;

    public UsersResponseDto(Users entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.pwd = entity.getPwd();
        this.name = entity.getName();
        this.birthday = entity.getBirthday();
        this.gender = entity.getGender();
        this.local = entity.getLocal();
        this.job = entity.getJob();
        this.interest = entity.getInterest();
        this.income = entity.getIncome();
        this.phone = entity.getPhone();
        this.signType = entity.getSignType();
        this.signToken = entity.getSignToken();
        this.appdoc_index = entity.getAppdoc_index();
    }
}
