package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.domain.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Getter
@Setter
@Builder
public class UsersListResponseDto {
    private String response_code;
    private Page<Users> users;

    @Builder
    public UsersListResponseDto(String response_code, Page<Users> users) {
        this.response_code = response_code;
        this.users = users;
    }
}
