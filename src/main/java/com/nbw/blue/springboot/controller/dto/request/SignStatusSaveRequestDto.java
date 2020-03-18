package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.signstatus.SignStatus;
import lombok.Builder;

public class SignStatusSaveRequestDto {
    private String uid;
    private boolean signStatus;

    @Builder
    public SignStatusSaveRequestDto(String uid,
                               boolean signStatus) {
        this.uid = uid;
        this.signStatus = signStatus;
    }

    public SignStatus toEntity() {
        return SignStatus.builder()
                .uid(uid)
                .sign_status(signStatus)
                .build();
    }
}
