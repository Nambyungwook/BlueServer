package com.nbw.blue.springboot.service.users;

import com.nbw.blue.springboot.domain.users.SignToken;
import com.nbw.blue.springboot.domain.users.SignTokenRepository;
import com.nbw.blue.springboot.error.ApplicationException;
import com.nbw.blue.springboot.error.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class SignTokenService {

    @Autowired
    private SignTokenRepository signTokenRepository;

    public String make(String uid) {

        SimpleDateFormat fomat_ = new SimpleDateFormat("yyyyMMdd");
        Date time = new Date();
        String now = fomat_.format(time);

        StringBuffer sb = new StringBuffer();
        String raw = uid+"_"+now;

        try {
            MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(raw.getBytes());

            byte[] msgStr = mDigest.digest() ;

            for(int i=0; i < msgStr.length; i++){
                String tmpEncTxt = Integer.toHexString((int)msgStr[i] & 0x00ff) ;
                sb.append(tmpEncTxt) ;
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return sb.toString() ;

    }


    @Transactional
    public SignToken updateAuthToken(String uid) {
        String token = make(uid);
        SignToken signToken = new SignToken();
        signToken.setToken(token);
        signToken.setUid(uid);
        return signTokenRepository.save(signToken);
    }

    public SignToken findByUid(String uid) {
        Optional<SignToken> authTokenOptional = signTokenRepository.findByUid(uid);
        if (authTokenOptional.isPresent()) {
            return authTokenOptional.get();
        } else {
            throw new ApplicationException(ResponseCode.INVALID_AUTH_TOKEN);
        }
    }


    public List<SignToken> findAll() {

        ArrayList<SignToken> Lists = new ArrayList<>();
        Lists.addAll((Collection<? extends SignToken>) signTokenRepository.findAll());

        return Lists;
    }

    public SignToken findByToken(String token) {
        Optional<SignToken> authTokenOptional = signTokenRepository.findByToken(token);
        if (authTokenOptional.isPresent()) {
            return authTokenOptional.get();
        } else {
            throw new ApplicationException(ResponseCode.INVALID_AUTH_TOKEN);
        }
    }

    @Transactional
    public long deleteToken(String uid) {
        return signTokenRepository.deleteByUid(uid);
    }
}
