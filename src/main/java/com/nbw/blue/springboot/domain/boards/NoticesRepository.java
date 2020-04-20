package com.nbw.blue.springboot.domain.boards;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticesRepository extends JpaRepository<Notices, Long> {

    Optional<Notices> findById(Long id);
}
