package com.nbw.blue.springboot.domain.sites;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSavedSitesRepository extends JpaRepository<UserSavedSites, Long> {
    List<UserSavedSites> findByUid(String uid);
}
