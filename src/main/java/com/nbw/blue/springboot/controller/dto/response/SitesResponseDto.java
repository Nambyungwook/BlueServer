package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.Sites;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SitesResponseDto {

    private Long id;
    private String site_name;
    private String category_1;
    private String category_2;
    private String category_3;
    private String site_url;

    @Builder
    public SitesResponseDto(Sites entity) {
        this.id = entity.getId();
        this.site_name = entity.getSite_name();
        this.category_1 = entity.getCategory_1();
        this.category_2 = entity.getCategory_2();
        this.category_3 = entity.getCategory_3();
        this.site_url = entity.getSite_url();
    }
}
