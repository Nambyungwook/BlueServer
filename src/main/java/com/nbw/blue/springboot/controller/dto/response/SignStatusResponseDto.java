package com.nbw.blue.springboot.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Getter
@Setter
@Builder
public class SignStatusResponseDto {

    private String uid;
    private boolean sign_status;

    @Builder
    public SignStatusResponseDto(String uid, boolean sign_status) {
        this.uid = uid;
        this.sign_status = sign_status;
    }
}
