package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.sites.Sites;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SitesSaveRequestDto {

    private String siteName;
    private String categoryB;
    private String categoryM;
    private String categoryS;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public SitesSaveRequestDto(String siteName,
                               String categoryB,
                               String categoryM,
                               String categoryS,
                               String siteUrl,
                               String siteDetail) {
        this.siteName = siteName;
        this.categoryB = categoryB;
        this.categoryM = categoryM;
        this.categoryS = categoryS;
        this.siteUrl = siteUrl;
        this.siteDetail =siteDetail;
    }

    public Sites toEntity() {
        return Sites.builder()
                .siteName(siteName)
                .categoryB(categoryB)
                .categoryM(categoryM)
                .categoryS(categoryS)
                .siteUrl(siteUrl)
                .siteDetail(siteDetail)
                .build();
    }
}
