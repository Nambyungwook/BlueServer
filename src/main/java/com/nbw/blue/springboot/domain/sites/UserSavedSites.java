package com.nbw.blue.springboot.domain.sites;

import com.nbw.blue.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "user_saved_sites")
public class UserSavedSites extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", columnDefinition = "varchar(10)", nullable = false)
    private String uid;

    @Column(name = "siteName", columnDefinition = "varchar(30)", nullable = false)
    private String siteName;

    @Column(name = "siteUrl", nullable = false)
    private String siteUrl;

    @Column(name = "siteDetail", nullable = false)
    private String siteDetail;

    @Builder
    public UserSavedSites(String uid,
                          String siteName,
                          String siteUrl,
                          String siteDetail) {
        this.uid = uid;
        this.siteName = siteName;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }
}
