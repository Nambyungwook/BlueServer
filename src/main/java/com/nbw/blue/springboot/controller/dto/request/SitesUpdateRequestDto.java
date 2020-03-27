package com.nbw.blue.springboot.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SitesUpdateRequestDto {

    private Long id;
    private String siteName;
    private String categoryB;
    private String categoryM;
    private String categoryS;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public SitesUpdateRequestDto(Long id,
                                 String siteName,
                                 String categoryB,
                                 String categoryM,
                                 String categoryS,
                                 String siteUrl,
                                 String siteDetail) {
        this.id = id;
        this.siteName = siteName;
        this.categoryB = categoryB;
        this.categoryM = categoryM;
        this.categoryS = categoryS;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }
}
