package com.nbw.blue.springboot.domain.boards;

import com.nbw.blue.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "notice")
public class Notices extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false)
    private String contents;

    @Column(name = "author", nullable = false)
    private String author;

    @Builder
    public Notices(String title, String contents, String author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }

    public void update(String title, String contents, String author) {
        this.title = title;
        this.contents = contents;
        this.author = author;
    }
}
