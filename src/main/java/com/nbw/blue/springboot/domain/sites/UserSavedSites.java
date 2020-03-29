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

    @Column(name = "siteId", nullable = false)
    private Long siteId;


    @Builder
    public UserSavedSites(String uid,
                          Long siteId) {
        this.uid = uid;
        this.siteId = siteId;
    }
}
