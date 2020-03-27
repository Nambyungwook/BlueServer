package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import lombok.Builder;


public class UserSavedSitesSaveRequestDto {
    private String uid;
    private String siteName;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public UserSavedSitesSaveRequestDto(String uid,
                                        String siteName,
                                        String siteUrl,
                                        String siteDetail) {
        this.uid = uid;
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }

    public UserSavedSites toEntity() {
        return UserSavedSites.builder()
                .uid(uid)
                .siteName(siteName)
                .siteUrl(siteUrl)
                .siteDetail(siteDetail)
                .build();
    }
}
