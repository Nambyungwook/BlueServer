package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.UsersResponseDto;
import com.nbw.blue.springboot.controller.dto.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.UsersUpdateRequestDto;
import com.nbw.blue.springboot.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping("/blue/v1/users")
    public Long save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    @PutMapping("/blue/v1/users/{id}")
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(id, requestDto);
    }

    @GetMapping("/blue/v1/users/{id}")
    public UsersResponseDto findById(@PathVariable Long id) {
        return usersService.findById(id);
    }
}
