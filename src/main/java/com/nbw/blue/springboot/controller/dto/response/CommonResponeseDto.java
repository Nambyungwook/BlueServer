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

    private String responese_code;
    private String message;

    @Builder
    public CommonResponeseDto(String responese_code, String message) {
        this.responese_code = responese_code;
        this.message = message;
    }
}
