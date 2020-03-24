package com.nbw.blue.springboot.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SitesUpdateRequestDto {

    private Long id;
    private String site_name;
    private String category_1;
    private String category_2;
    private String category_3;
    private String site_url;

    @Builder
    public SitesUpdateRequestDto(Long id,
                                 String site_name,
                                 String category_1,
                                 String category_2,
                                 String category_3,
                                 String site_url) {
        this.id = id;
        this.site_name = site_name;
        this.category_1 = category_1;
        this.category_2 = category_2;
        this.category_3 = category_3;
        this.site_url = site_url;
    }
}
