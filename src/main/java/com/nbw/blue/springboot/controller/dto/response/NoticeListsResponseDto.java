package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.boards.Notices;
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
public class NoticeListsResponseDto {

    private String responseCode;
    private String message;
    private List<Notices> notices;

    @Builder
    public NoticeListsResponseDto(String responseCode, String message, List<Notices> notices) {
        this.responseCode = responseCode;
        this.message = message;
        this.notices = notices;
    }
}
