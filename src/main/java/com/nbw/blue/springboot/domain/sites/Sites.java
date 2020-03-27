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

    @Column(name = "siteName", columnDefinition = "varchar(30)", nullable = false)
    private String siteName;

    @Column(name = "categoryB", columnDefinition = "varchar(30)", nullable = false)
    private String categoryB;

    @Column(name = "categoryM",columnDefinition = "varchar(30)", nullable = false)
    private String categoryM;

    @Column(name = "categoryS", columnDefinition = "varchar(30)")
    private String categoryS;

    @Column(name = "siteUrl", nullable = false)
    private String siteUrl;

    @Column(name = "siteDetail", nullable = false)
    private String siteDetail;

    @Builder
    public Sites(String siteName,
                 String categoryB,
                 String categoryM,
                 String categoryS,
                 String siteUrl,
                 String siteDetail) {

        this.siteName = siteName;
        this.categoryB = categoryB;
        this.categoryM = categoryM;
        this.categoryS = categoryS;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }

    public void update(String siteName,
                       String categoryB,
                       String categoryM,
                       String categoryS,
                       String siteUrl,
                       String siteDetail) {

        this.siteName = siteName;
        this.categoryB = categoryB;
        this.categoryM = categoryM;
        this.categoryS = categoryS;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }
}
