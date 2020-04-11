package com.nbw.blue.springboot.service.users;

import com.nbw.blue.springboot.controller.dto.request.*;
import com.nbw.blue.springboot.controller.dto.response.*;
import com.nbw.blue.springboot.domain.signstatus.SignStatus;
import com.nbw.blue.springboot.domain.signstatus.SignStatusRepository;
import com.nbw.blue.springboot.domain.sites.UserSavedSites;
import com.nbw.blue.springboot.domain.sites.UserSavedSitesRepository;
import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.domain.users.UsersRepository;
import com.nbw.blue.springboot.error.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final SignStatusRepository signStatusRepository;
    private final UserSavedSitesRepository userSavedSitesRepository;

    //사용자 정보 저장 - 회원가입
    @Transactional
    public CommonResponeseDto save(UsersSaveRequestDto requestDto) {
        //이미 존재하는 이메일인지 확인
        if (usersRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            return new CommonResponeseDto("FAIL", ResponseCode.ALREADY_EXIST_USER.getMessage(), null);
        } else {
            Users users = usersRepository.save(requestDto.toEntity());
            return new CommonResponeseDto("SUCCESS", ResponseCode.SUCCESS.getMessage(), users.getUid());
        }
    }

    //사용자별 사이트 저장
    @Transactional
    public UserSavedSitesResponseDto saveSites(UserSavedSitesSaveRequestDto requestDto) {
        Long savedSiteId = requestDto.toEntity().getSiteId();
        if (userSavedSitesRepository.findAllByUidContainingAndSiteId(requestDto.toEntity().getUid(), savedSiteId).isPresent()) {
            return new UserSavedSitesResponseDto(requestDto.toEntity(), "FAIL");
        }
        return new UserSavedSitesResponseDto(userSavedSitesRepository.save(requestDto.toEntity()), "SUCCESS");
    }

    //사용자별 사이트 삭제 - uid와 사이트 id 이용
    @Transactional
    public CommonResponeseDto deleteSites(String uid, Long siteId) {

        List<UserSavedSites> userSavedSitesList = userSavedSitesRepository.findByUid(uid);

        int cnt = 0;
        for (int i=0; i<userSavedSitesList.size(); i++) {
            if (userSavedSitesList.get(i).getSiteId().equals(siteId)) {
                userSavedSitesRepository.delete(userSavedSitesList.get(i));

                break;
            }
            cnt++;

            if (cnt==userSavedSitesList.size()) {
                CommonResponeseDto responeseDto = new CommonResponeseDto("FAIL", "해당 사이트가 업습니다. siteId = "+siteId, siteId+"");
                return responeseDto;
            }
        }

        CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "저장한 사이트 제거 성공. siteId = "+siteId, siteId+"");
        return responeseDto;
    }

    //사용자 정보 수정
    @Transactional
    public UsersResponseDto update(String uid, UsersUpdateRequestDto requestDto) {

        Users users = usersRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. uid=" + uid));

        //지금 입력한 전화번호와 저장된 번호가 같은지 확인
        if (!requestDto.getPhone().equals(users.getPhone())) {
            //다른 전화번호인데 전화번호가 이미 존재하면 새로 저장하지 않음
            if (usersRepository.findByPhone(requestDto.getPhone()).isPresent()) {
                return new UsersResponseDto("FAIL",users);
            } else {
                //다른 전화 번호인데 전화번호가 존재 하지 않으므로 바뀐 내용을 저장
                users.update(requestDto.getName(),
                        requestDto.getBirthday(),
                        requestDto.getGender(),
                        requestDto.getLocal(),
                        requestDto.getJob(),
                        requestDto.getInterest(),
                        requestDto.getIncome(),
                        requestDto.getPhone(),
                        requestDto.getAppdoc_index());

                return new UsersResponseDto("SUCCESS",users);
            }
        } else {
            //원래 입력되어있는 번호와 같기 때문에 그냥 저장
            users.update(requestDto.getName(),
                    requestDto.getBirthday(),
                    requestDto.getGender(),
                    requestDto.getLocal(),
                    requestDto.getJob(),
                    requestDto.getInterest(),
                    requestDto.getIncome(),
                    requestDto.getPhone(),
                    requestDto.getAppdoc_index());

            return new UsersResponseDto("SUCCESS",users);
        }
    }

    //사용자 삭제 - index사용
    @Transactional
    public void delete(Long id) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        usersRepository.delete(users);
    }

    //로그인
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

    //로그아웃
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

    //회원탈퇴
    @Transactional
    public CommonResponeseDto dropUser(String uid) {
        Users users = usersRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        SignStatus signStatus = signStatusRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        //사용자 데이터 삭제
        usersRepository.delete(users);
        //사용자 로그인 상태 삭제
        signStatusRepository.delete(signStatus);
        //사용자가 저장한 사이트 목록 전체 삭제
        List<UserSavedSites> userSavedSitesList = userSavedSitesRepository.findAllByUid(uid);
        for (int i=0; i<userSavedSitesList.size(); i++) {
            userSavedSitesRepository.delete(userSavedSitesList.get(i));
        }

        CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "회원탈퇴 성공", uid);
        return responeseDto;
    }

    //로그인 상태 조회
    @Transactional
    public SignStatusResponseDto getStatus(String uid) {

        if (usersRepository.findByUid(uid).isPresent()) {
            SignStatus signStatus = signStatusRepository.findByUid(uid).orElse(SignStatus.builder().uid(uid).sign_status(true).build());

            if (signStatus.isSign_status()) {
                SignStatusResponseDto signStatusResponseDto = new SignStatusResponseDto(uid, true);
                return signStatusResponseDto;
            } else {
                SignStatusResponseDto signStatusResponseDto = new SignStatusResponseDto(uid, false);
                return signStatusResponseDto;
            }
        } else {
            return new SignStatusResponseDto(uid, false);
        }
    }

    //uid로 사용자 검색
    public UsersResponseDto findByUid(String uid) {
        Users entity = usersRepository.findByUid(uid).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. uid=" + uid));

        return new UsersResponseDto("SUCCESS", entity);
    }

    //email로 사용자 검색
    public UsersResponseDto findByEmail(String email) {

        if (usersRepository.findByEmail(email).isPresent()) {
            Users entity = usersRepository.findByEmail(email).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. email=" + email));
            return new UsersResponseDto("SUCCESS", entity);
        } else {
            Users entity = Users.builder().build();
            return new UsersResponseDto("FAIL", entity);
        }
    }

    //id로 사용자 검색
    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UsersResponseDto("SUCCESS", entity);
    }

    //전체 사용자 조회
    public UsersListResponseDto findAll(Pageable pageable) {
        Page<Users> allUsersList = usersRepository.findAll(pageable);

        return new UsersListResponseDto("SUCCESS", allUsersList);
    }

    //서버 연결되어있는지 확인하는 코드
    public CommonResponeseDto checkServer() {
        return new CommonResponeseDto("SUCCESS", "SERVER_CONNECT", "000000");
    }
}
