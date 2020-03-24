package com.nbw.blue.springboot.domain.sites;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SitesRepository extends JpaRepository<Sites, Long> {
    Optional<Sites> findById(Long id);
}
