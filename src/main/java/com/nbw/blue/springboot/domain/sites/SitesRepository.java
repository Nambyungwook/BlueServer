package com.nbw.blue.springboot.domain.sites;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SitesRepository extends JpaRepository<Sites, Long> {
    Optional<Sites> findById(Long id);
    List<Sites> findByTargetMain(String targetMain);
    List<Sites> findByTargetDetail(String targetDetail);
    List<Sites> findByLocal(String local);
    List<Sites> findByIncome(String income);
    List<Sites> findByAge(String age);
    List<Sites> findByGender(String gender);
    List<Sites> findBySiteName(String siteName);

    List<Sites> findAllByTargetMainContainingAndTargetDetailContainingAndLocalContainingAndSubLocalContainingAndIncomeContainingAndAgeContainingAndGenderContainingAndSiteNameContainingOrderById(String targetMain,
                                                                                                                                                                                                  String targetDetail,
                                                                                                                                                                                                  String local,
                                                                                                                                                                                                  String subLocal,
                                                                                                                                                                                                  String income,
                                                                                                                                                                                                  String age,
                                                                                                                                                                                                  String gender,
                                                                                                                                                                                                  String siteName);
}
