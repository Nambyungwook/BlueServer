package com.nbw.blue.springboot.domain.sites;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SitesRepository extends JpaRepository<Sites, Long> {
    Optional<Sites> findById(Long id);
    List<Sites> findByCategoryB(String categoryB);
    List<Sites> findByCategoryM(String categoryM);
    List<Sites> findByCategoryS(String categoryS);
    List<Sites> findBySiteName(String siteName);
}
