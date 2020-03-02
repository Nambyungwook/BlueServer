package com.nbw.blue.springboot.domain.users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SignTokenRepository extends CrudRepository<SignToken, String> {

    Optional<SignToken> findByUid(String uid);
    Optional<SignToken> findByToken(String token);
    long deleteByUid(String uid);

}
