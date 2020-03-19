package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.UsersSigninRequestDto;
import com.nbw.blue.springboot.controller.dto.response.CommonResponeseDto;
import com.nbw.blue.springboot.controller.dto.response.SignStatusResponseDto;
import com.nbw.blue.springboot.controller.dto.response.UsersResponseDto;
import com.nbw.blue.springboot.controller.dto.request.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersUpdateRequestDto;
import com.nbw.blue.springboot.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    //서버확인
    @GetMapping("/blue/v1/check")
    public CommonResponeseDto checkServer() {
        return usersService.checkServer();
    }

    //회원가입
    @PostMapping("/blue/v1/users/signup")
    public UsersResponseDto save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    //회원정보수정
    @PutMapping("/blue/v1/users/update/{uid}")
    public UsersResponseDto update(@PathVariable String uid, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(uid, requestDto);
    }

    //회원정보조회
    @GetMapping("/blue/v1/users/userinfo/{uid}")
    public UsersResponseDto findByUid(@PathVariable String uid) {
        return usersService.findByUid(uid);
    }

    //회원정보삭제 - index로 삭제
    @DeleteMapping("/blue/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        usersService.delete(id);

        return id;
    }

    //로그인시 회원정보가 올바르면 true반환
    @PostMapping("/blue/v1/users/signin")
    public CommonResponeseDto signin(@RequestBody UsersSigninRequestDto requestDto) {

        return usersService.signin(requestDto);
    }

    //로그아웃
    @GetMapping("/blue/v1/users/signout/{uid}")
    public CommonResponeseDto signout(@PathVariable String uid) {
        return usersService.signout(uid);
    }

    //회원탈퇴
    @GetMapping("/blue/v1/users/dropout/{uid}")
    public CommonResponeseDto dropout(@PathVariable String uid) {

        return usersService.dropUser(uid);
    }

    //로그인상태 조회
    @GetMapping("/blue/v1/users/status/{uid}")
    public SignStatusResponseDto getStatus(@PathVariable String uid) {
        return usersService.getStatus(uid);
    }
}
