package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.sites.Sites;
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

    private String responseCode;
    private List<Sites> savedSites;

    @Builder
    public UserSavedSitesListResponseDto(String responseCode, List<Sites> savedSites) {
        this.responseCode = responseCode;
        this.savedSites = savedSites;
    }
}
