package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserSavedSitesResponseDto {

    private Long id;
    private String uid;
    private String site_name;
    private String site_url;

    @Builder
    public UserSavedSitesResponseDto(UserSavedSites entity) {
        this.id = entity.getId();
        this.uid = entity.getUid();
        this.site_name = entity.getSite_name();
        this.site_url = entity.getSite_url();
    }
}
