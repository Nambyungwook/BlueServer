package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersUpdateRequestDto;
import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.domain.users.UsersRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UsersRepository usersRepository;

    @After
    public void tearDown() throws Exception{
        usersRepository.deleteAll();
    }

    @Test
    public void Users_등록된다() throws Exception {
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
        String signToken = "";
        Long appdoc_index = Long.valueOf(1);

        UsersSaveRequestDto requestDto = UsersSaveRequestDto.builder()
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

        String url = "http://localhost:" + port + "/blue/v1/users";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getEmail()).isEqualTo(email);
        assertThat(all.get(0).getPwd()).isEqualTo(pwd);
    }

    @Test
    public void Users_수정된다() throws Exception {
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
        String phone = "010-0000-0000";
        String signType = "M";
        String signToken = "";
        Long appdoc_index = Long.valueOf(1);

        Users savedUsers = usersRepository.save(Users.builder()
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
                .build());

        Long updateId = savedUsers.getId();
        String expectedPhone = "010-6267-6837";
        String expectedJob = "rich";
        Integer expectedIncome = 10000;

        UsersUpdateRequestDto requestDto = UsersUpdateRequestDto.builder()
                .phone(expectedPhone)
                .job(expectedJob)
                .income(expectedIncome)
                .build();

        String url = "http://localhost:" + port + "/blue/v1/users/" + updateId;

        HttpEntity<UsersUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Users> all = usersRepository.findAll();
        assertThat(all.get(0).getPhone()).isEqualTo(expectedPhone);
        assertThat(all.get(0).getJob()).isEqualTo(expectedJob);
        assertThat(all.get(0).getIncome()).isEqualTo(expectedIncome);
    }
}
