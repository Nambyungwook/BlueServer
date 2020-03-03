package com.nbw.blue.springboot.domain.signstatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignStatusRepository extends JpaRepository<SignStatus, Long> {
    Optional<SignStatus> findByUid(String uid);//유저 중복 확인용
}
