package com.nbw.blue.springboot.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);//이메일 중복 확인용
    Optional<Users> findByUid(String uid);
}
