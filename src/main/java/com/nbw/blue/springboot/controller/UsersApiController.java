package com.nbw.blue.springboot.controller;

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

    //회원가입
    @PostMapping("/blue/v1/users")
    public Long save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    //회원정보수정
    @PutMapping("/blue/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(id, requestDto);
    }

    //회원정보조회
    @GetMapping("/blue/v1/users/{id}")
    public UsersResponseDto findById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    //회원정보삭제
    @DeleteMapping("/blue/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        usersService.delete(id);

        return id;
    }
}
