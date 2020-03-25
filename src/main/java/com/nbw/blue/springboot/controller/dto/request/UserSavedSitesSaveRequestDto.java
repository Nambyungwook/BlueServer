package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import lombok.Builder;


public class UserSavedSitesSaveRequestDto {
    private String uid;
    private String site_name;
    private String site_url;

    @Builder
    public UserSavedSitesSaveRequestDto(String uid,
                                        String site_name,
                                        String site_url) {
        this.uid = uid;
        this.site_name = site_name;
        this.site_url = site_url;
    }

    public UserSavedSites toEntity() {
        return UserSavedSites.builder()
                .uid(uid)
                .site_name(site_name)
                .site_url(site_url)
                .build();
    }
}
