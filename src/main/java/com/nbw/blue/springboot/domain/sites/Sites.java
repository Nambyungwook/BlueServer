package com.nbw.blue.springboot.domain.sites;

import com.nbw.blue.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "sites")
public class Sites extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "site_name", columnDefinition = "varchar(30)", nullable = false)
    private String site_name;

    @Column(name = "category_1", columnDefinition = "varchar(30)", nullable = false)
    private String category_1;

    @Column(name = "category_2",columnDefinition = "varchar(30)", nullable = false)
    private String category_2;

    @Column(name = "category_3", columnDefinition = "varchar(30)")
    private String category_3;

    @Column(name = "site_url", nullable = false)
    private String site_url;

    @Builder
    public Sites(String site_name,
                 String category_1,
                 String category_2,
                 String category_3,
                 String site_url) {

        this.site_name = site_name;
        this.category_1 = category_1;
        this.category_2 = category_2;
        this.category_3 = category_3;
        this.site_url = site_url;
    }

    public void update(String site_name,
                       String category_1,
                       String category_2,
                       String category_3,
                       String site_url) {

        this.site_name = site_name;
        this.category_1 = category_1;
        this.category_2 = category_2;
        this.category_3 = category_3;
        this.site_url = site_url;
    }
}
