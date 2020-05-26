package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.Sites;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SitesResponseDto {

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
    public SitesResponseDto(Sites entity) {
        this.id = entity.getId();
        this.siteName = entity.getSiteName();
        this.targetMain = entity.getTargetMain();
        this.targetDetail = entity.getTargetDetail();
        this.local = entity.getLocal();
        this.subLocal = entity.getSubLocal();
        this.income = entity.getIncome();
        this.age = entity.getAge();
        this.gender = entity.getGender();
        this.siteUrl = entity.getSiteUrl();
        this.siteDetail = entity.getSiteDetail();
    }
}
