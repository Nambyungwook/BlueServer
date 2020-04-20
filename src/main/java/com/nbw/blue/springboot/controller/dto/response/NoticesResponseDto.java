package com.nbw.blue.springboot.controller.dto.response;

import com.nbw.blue.springboot.domain.boards.Notices;
import lombok.Getter;

@Getter
public class NoticesResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String author;

    public NoticesResponseDto(Notices entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.author = entity.getAuthor();
    }
}