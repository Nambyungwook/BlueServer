package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Getter
@Setter
@Builder
public class UsersListResponseDto {
    private String responseCode;
    private Page<Users> users;

    @Builder
    public UsersListResponseDto(String responseCode, Page<Users> users) {
        this.responseCode = responseCode;
        this.users = users;
    }
}
