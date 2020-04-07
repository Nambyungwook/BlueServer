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

    private String responseCode;
    private String message;
    private String uid;

    @Builder
    public CommonResponeseDto(String responseCode, String message, String uid) {
        this.responseCode = responseCode;
        this.message = message;
        this.uid = uid;
    }
}
