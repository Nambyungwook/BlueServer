package com.nbw.blue.springboot.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SitesUpdateRequestDto {

    private Long id;
    private String siteName;
    private String targetMain;
    private String targetDetail;
    private String local;
    private String subLocal;
    private String income;
    private String age;
    private String gender;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public SitesUpdateRequestDto(Long id,
                                 String siteName,
                                 String targetMain,
                                 String targetDetail,
                                 String local,
                                 String subLocal,
                                 String income,
                                 String age,
                                 String gender,
                                 String siteUrl,
                                 String siteDetail) {
        this.id = id;
        this.siteName = siteName;
        this.targetMain = targetMain;
        this.targetDetail = targetDetail;
        this.local = local;
        this.subLocal = subLocal;
        this.income = income;
        this.age = age;
        this.gender = gender;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }
}
