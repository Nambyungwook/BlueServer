package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.Sites;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SitesResponseDto {

    private Long id;
    private String siteName;
    private String categoryB;
    private String categoryM;
    private String categoryS;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public SitesResponseDto(Sites entity) {
        this.id = entity.getId();
        this.siteName = entity.getSiteName();
        this.categoryB = entity.getCategoryB();
        this.categoryM = entity.getCategoryM();
        this.categoryS = entity.getCategoryS();
        this.siteUrl = entity.getSiteUrl();
        this.siteDetail = entity.getSiteDetail();
    }
}
