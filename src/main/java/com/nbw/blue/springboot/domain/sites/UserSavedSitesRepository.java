package com.nbw.blue.springboot.domain.sites;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserSavedSitesRepository extends JpaRepository<UserSavedSites, Long> {
    List<UserSavedSites> findByUid(String uid);
    Optional<UserSavedSites> findById(Long id);
    Optional<UserSavedSites> findAllByUidContainingAndId(String uid, Long id);
}
