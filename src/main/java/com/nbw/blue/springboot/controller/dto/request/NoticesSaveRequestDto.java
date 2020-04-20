package com.nbw.blue.springboot.controller.dto.request;

import com.nbw.blue.springboot.domain.boards.Notices;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticesSaveRequestDto {

    private String title;
    private String contents;
    private String author;

    @Builder
    public NoticesSaveRequestDto(String title, String contents, String author) {

        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public Notices toEntity() {
        return Notices.builder()
                .title(title)
                .contents(contents)
                .author(author)
                .build();
    }
}
