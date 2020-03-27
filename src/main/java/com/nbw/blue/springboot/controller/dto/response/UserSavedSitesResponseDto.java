package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSavedSitesResponseDto {

    private Long id;
    private String uid;
    private String siteName;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public UserSavedSitesResponseDto(UserSavedSites entity) {
        this.id = entity.getId();
        this.uid = entity.getUid();
        this.siteName = entity.getSiteName();
        this.siteUrl = entity.getSiteUrl();
        this.siteDetail = entity.getSiteDetail();
    }
}
