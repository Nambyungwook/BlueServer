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

    @Column(name = "site_name", columnDefinition = "varchar(30)", nullable = false)
    private String site_name;

    @Column(name = "site_url", nullable = false)
    private String site_url;

    @Builder
    public UserSavedSites(String uid,
                          String site_name,
                          String site_url) {
        this.uid = uid;
        this.site_name = site_name;
        this.site_url = site_url;
    }
}
