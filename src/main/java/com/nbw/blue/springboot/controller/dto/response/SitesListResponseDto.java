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
public class SitesListResponseDto {
    private String responseCode;
    private List<Sites> sites;

    @Builder
    public SitesListResponseDto(String responseCode, List<Sites> sites) {
        this.responseCode = responseCode;
        this.sites = sites;
    }
}
