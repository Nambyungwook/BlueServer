package com.nbw.blue.springboot.controller.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Getter
@Setter
@Builder
public class CommonResponeseDto {

    private String response_code;
    private String message;
    private String uid;

    @Builder
    public CommonResponeseDto(String response_code, String message, String uid) {
        this.response_code = response_code;
        this.message = message;
        this.uid = uid;
    }
}
