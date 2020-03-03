package com.nbw.blue.springboot.domain.users;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersRepositoryTest {

    @Autowired
    UsersRepository usersRepository;

    @After
    public void cleanup() {
        usersRepository.deleteAll();
    }

    @Test
    public void User정보_불러오기() {
        //given
        String email = "skaquddnr21@naver.com";
        String pwd ="1q2w3e4r";
        String name = "남병욱";
        String birthday = "1993-09-03";
        String gender = "M";
        String local = "경기도 김포시";
        String job = "학생";
        String interest = "컴퓨터";
        Integer income = 4000;//만원
        String phone = "010-6267-6837";
        String signType = "M";
        Long appdoc_index = Long.valueOf(1);

        usersRepository.save(Users.builder()
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
            .build());

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(0);
        assertThat(users.getEmail()).isEqualTo(email);
        assertThat(users.getPwd()).isEqualTo(pwd);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        String email = "skaquddnr21@naver.com";
        String pwd ="1q2w3e4r";
        String name = "남병욱";
        String birthday = "1993-09-03";
        String gender = "M";
        String local = "경기도 김포시";
        String job = "학생";
        String interest = "컴퓨터";
        Integer income = 4000;//만원
        String phone = "010-6267-6837";
        String signType = "M";
        Long appdoc_index = Long.valueOf(1);

        LocalDateTime now = LocalDateTime.of(2020,3,1,0,0,0);
        usersRepository.save(Users.builder()
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
                .build());

        //when
        List<Users> usersList = usersRepository.findAll();

        //then
        Users users = usersList.get(0);

        System.out.println(">>>>>>>>>> createDate="+users.getCreatedDate()+", modifiedDate="+users.getModifiedDate());

        assertThat(users.getCreatedDate()).isAfter(now);
        assertThat(users.getModifiedDate()).isAfter(now);
    }
}
