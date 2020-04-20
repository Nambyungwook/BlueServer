package com.nbw.blue.springboot.controller.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NoticesUpdateRequestDto {

    private Long id;
    private String title;
    private String contents;
    private String author;

    @Builder
    public NoticesUpdateRequestDto(String title, String contents, String author) {

        this.title = title;
        this.contents = contents;
        this.author = author;
    }
}
