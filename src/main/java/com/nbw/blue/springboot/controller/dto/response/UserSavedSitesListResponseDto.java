package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ResponseBody
@Getter
@Setter
@Builder
public class UserSavedSitesListResponseDto {

    private String response_code;
    private List<UserSavedSites> sites;

    @Builder
    public UserSavedSitesListResponseDto(String response_code, List<UserSavedSites> sites) {
        this.response_code = response_code;
        this.sites = sites;
    }
}
