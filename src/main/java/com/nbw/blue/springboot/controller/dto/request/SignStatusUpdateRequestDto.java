package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.signstatus.SignStatus;
import lombok.Builder;

public class SignStatusUpdateRequestDto {

    private String uid;
    private boolean signStatus;

    @Builder
    public SignStatusUpdateRequestDto(String uid,
                                    boolean signStatus) {
        this.uid = uid;
        this.signStatus = signStatus;
    }
}
