package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import lombok.Builder;


public class UserSavedSitesSaveRequestDto {
    private String uid;
    private Long siteId;

    @Builder
    public UserSavedSitesSaveRequestDto(String uid,
                                        Long siteId) {
        this.uid = uid;
        this.siteId = siteId;
    }

    public UserSavedSites toEntity() {
        return UserSavedSites.builder()
                .uid(uid)
                .siteId(siteId)
                .build();
    }
}
