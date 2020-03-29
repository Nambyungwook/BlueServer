package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.sites.Sites;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SitesSaveRequestDto {

    private String siteName;
    private String targetMain;
    private String targetDetail;
    private String local;
    private String income;
    private String age;
    private String gender;
    private String siteUrl;
    private String siteDetail;

    @Builder
    public SitesSaveRequestDto(String siteName,
                               String targetMain,
                               String targetDetail,
                               String local,
                               String income,
                               String age,
                               String gender,
                               String siteUrl,
                               String siteDetail) {
        this.siteName = siteName;
        this.targetMain = targetMain;
        this.targetDetail = targetDetail;
        this.local = local;
        this.income = income;
        this.age = age;
        this.gender = gender;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }

    public Sites toEntity() {
        return Sites.builder()
                .siteName(siteName)
                .targetMain(targetMain)
                .targetDetail(targetDetail)
                .local(local)
                .income(income)
                .age(age)
                .gender(gender)
                .siteUrl(siteUrl)
                .siteDetail(siteDetail)
                .build();
    }
}
