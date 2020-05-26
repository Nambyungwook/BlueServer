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
public class Sites extends BaseTimeEntity implements Comparable<Sites>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "siteName", columnDefinition = "varchar(30)", nullable = false)
    private String siteName;

    @Column(name = "targetMain", columnDefinition = "varchar(30)", nullable = false)
    private String targetMain;

    @Column(name = "targetDetail",columnDefinition = "varchar(30)", nullable = false)
    private String targetDetail;

    @Column(name = "local", columnDefinition = "varchar(30)", nullable = false)
    private String local;

    @Column(name = "subLocal", columnDefinition = "varchar(30)", nullable = false)
    private String subLocal;

    @Column(name = "income", columnDefinition = "varchar(30)", nullable = false)
    private String income;

    @Column(name = "age", columnDefinition = "varchar(30)", nullable = false)
    private String age;

    @Column(name = "gender", columnDefinition = "varchar(30)", nullable = false)
    private String gender;

    @Column(name = "siteUrl", nullable = false)
    private String siteUrl;

    @Column(name = "siteDetail", length = 1000, nullable = false)
    private String siteDetail;

    @Builder
    public Sites(String siteName,
                 String targetMain,
                 String targetDetail,
                 String local,
                 String subLocal,
                 String income,
                 String age,
                 String gender,
                 String siteUrl,
                 String siteDetail) {

        this.siteName = siteName;
        this.targetMain = targetMain;
        this.targetDetail = targetDetail;
        this.local = local;
        this.subLocal = subLocal;
        this.income = income;
        this.age = age;
        this.gender = gender;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }

    public void update(String siteName,
                       String targetMain,
                       String targetDetail,
                       String local,
                       String subLocal,
                       String income,
                       String age,
                       String gender,
                       String siteUrl,
                       String siteDetail) {

        this.siteName = siteName;
        this.targetMain = targetMain;
        this.targetDetail = targetDetail;
        this.local = local;
        this.subLocal = subLocal;
        this.income = income;
        this.age = age;
        this.gender = gender;
        this.siteUrl = siteUrl;
        this.siteDetail = siteDetail;
    }

    @Override
    public int compareTo(Sites o) {
        String sitesName = o.getSiteName();

        return -sitesName.compareToIgnoreCase(getSiteName());
    }
}
