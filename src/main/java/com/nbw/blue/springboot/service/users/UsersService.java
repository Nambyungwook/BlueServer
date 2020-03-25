package com.nbw.blue.springboot.service.users;

import com.nbw.blue.springboot.controller.dto.request.*;
import com.nbw.blue.springboot.controller.dto.response.CommonResponeseDto;
import com.nbw.blue.springboot.controller.dto.response.SignStatusResponseDto;
import com.nbw.blue.springboot.controller.dto.response.UserSavedSitesResponseDto;
import com.nbw.blue.springboot.controller.dto.response.UsersResponseDto;
import com.nbw.blue.springboot.domain.signstatus.SignStatus;
import com.nbw.blue.springboot.domain.signstatus.SignStatusRepository;
import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import com.nbw.blue.springboot.domain.sites.UserSavedSitesRepository;
import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final SignStatusRepository signStatusRepository;
    private final UserSavedSitesRepository userSavedSitesRepository;

    @Transactional
    public UsersResponseDto save(UsersSaveRequestDto requestDto) {
        return new UsersResponseDto(usersRepository.save(requestDto.toEntity()));
    }

    @Transactional
    public UserSavedSitesResponseDto saveSites(UserSavedSitesSaveRequestDto requestDto) {
        return new UserSavedSitesResponseDto(userSavedSitesRepository.save(requestDto.toEntity()));
    }

    @Transactional
    public CommonResponeseDto deleteSites(String uid, String siteName) {

        List<UserSavedSites> userSavedSitesList = userSavedSitesRepository.findByUid(uid);

        int cnt = 0;
        for (int i=0; i<userSavedSitesList.size(); i++) {
            if (userSavedSitesList.get(i).getSite_name().equals(siteName)) {
                userSavedSitesRepository.delete(userSavedSitesList.get(i));

                break;
            }
            cnt++;

            if (cnt==userSavedSitesList.size()) {
                CommonResponeseDto responeseDto = new CommonResponeseDto("FAIL", "해당 사이트가 업습니다.", siteName);
                return responeseDto;
            }
        }

        CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "저장한 사이트 제거 성공", siteName);
        return responeseDto;
    }

    @Transactional
    public UsersResponseDto update(String uid, UsersUpdateRequestDto requestDto) {
        Users users = usersRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. uid=" + uid));
        users.update(requestDto.getName(),
                     requestDto.getBirthday(),
                     requestDto.getGender(),
                     requestDto.getLocal(),
                     requestDto.getJob(),
                     requestDto.getInterest(),
                     requestDto.getIncome(),
                     requestDto.getPhone(),
                     requestDto.getAppdoc_index());

        return new UsersResponseDto(users);
    }

    @Transactional
    public void delete(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        usersRepository.delete(users);
    }

    @Transactional
    public CommonResponeseDto signin(UsersSigninRequestDto requestDto) {
        Users users = usersRepository.findByEmail(requestDto.getEmail()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        String tmp_uid = users.getUid();

        if (!signStatusRepository.findByUid(tmp_uid).isPresent()) {
            SignStatusSaveRequestDto signStatusSaveRequestDto = new SignStatusSaveRequestDto(tmp_uid, false);
            signStatusRepository.save(signStatusSaveRequestDto.toEntity());
        }

        SignStatus signStatus = signStatusRepository.findByUid(tmp_uid).get();
        if (users.getPwd().equals(requestDto.getPwd())) {
            signStatus.update(tmp_uid, true);
            CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "로그인 성공", users.getUid());
            return responeseDto;
        } else {
            signStatus.update(tmp_uid, false);
            CommonResponeseDto responeseDto = new CommonResponeseDto("FAIL", "로그인 실패", users.getUid());
            return responeseDto;
        }
    }

    @Transactional
    public CommonResponeseDto signout(String uid) {

        SignStatus signStatus = signStatusRepository.findByUid(uid).orElse(SignStatus.builder().uid(uid).sign_status(true).build());

        if (signStatus.isSign_status()) {
            signStatus.update(uid, false);

            CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "로그아웃 성공", uid);
            return responeseDto;
        } else {
            CommonResponeseDto responeseDto = new CommonResponeseDto("FAIL", "로그아웃 실패", uid);
            return responeseDto;
        }
    }

    @Transactional
    public CommonResponeseDto dropUser(String uid) {
        Users users = usersRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        SignStatus signStatus = signStatusRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        usersRepository.delete(users);
        signStatusRepository.delete(signStatus);

        CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "회원탈퇴 성공", uid);
        return responeseDto;
    }

    @Transactional
    public SignStatusResponseDto getStatus(String uid) {
        SignStatus signStatus = signStatusRepository.findByUid(uid).orElse(SignStatus.builder().uid(uid).sign_status(true).build());

        if (signStatus.isSign_status()) {
            SignStatusResponseDto signStatusResponseDto = new SignStatusResponseDto(uid, true);
            return signStatusResponseDto;
        } else {
            SignStatusResponseDto signStatusResponseDto = new SignStatusResponseDto(uid, false);
            return signStatusResponseDto;
        }
    }

    public UsersResponseDto findByUid(String uid) {
        Users entity = usersRepository.findByUid(uid).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. uid=" + uid));

        return new UsersResponseDto(entity);
    }

    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UsersResponseDto(entity);
    }

    public CommonResponeseDto checkServer() {
        return new CommonResponeseDto("SUCCESS", "SERVER_CONNECT", "000000");
    }
}
