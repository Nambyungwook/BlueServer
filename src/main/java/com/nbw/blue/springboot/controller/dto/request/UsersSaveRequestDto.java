package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.utils.UidGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {
    private String uid;
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
    private Long appdoc_index;
    private String terms_agree;

    @Builder
    public UsersSaveRequestDto(String uid,
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
                               String signType,
                               Long appdoc_index,
                               String terms_agree) {
        this.uid = uid;
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
        this.appdoc_index = appdoc_index;
        this.terms_agree = terms_agree;
    }

    public Users toEntity() {
        return Users.builder()
                .uid(UidGenerator.make())
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
                .appdoc_index(appdoc_index)
                .terms_agree(terms_agree)
                .build();
    }
}
