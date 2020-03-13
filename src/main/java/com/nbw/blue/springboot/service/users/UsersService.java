package com.nbw.blue.springboot.service.users;

import com.nbw.blue.springboot.controller.dto.request.UsersSigninRequestDto;
import com.nbw.blue.springboot.controller.dto.response.CommonResponeseDto;
import com.nbw.blue.springboot.controller.dto.response.SignStatusResponseDto;
import com.nbw.blue.springboot.controller.dto.response.UsersResponseDto;
import com.nbw.blue.springboot.controller.dto.request.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersUpdateRequestDto;
import com.nbw.blue.springboot.domain.signstatus.SignStatus;
import com.nbw.blue.springboot.domain.signstatus.SignStatusRepository;
import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final SignStatusRepository signStatusRepository;

    @Transactional
    public Long save(UsersSaveRequestDto requestDto) {
        return usersRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, UsersUpdateRequestDto requestDto) {
        Users users = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        users.update(requestDto.getName(),
                     requestDto.getBirthday(),
                     requestDto.getGender(),
                     requestDto.getLocal(),
                     requestDto.getJob(),
                     requestDto.getInterest(),
                     requestDto.getIncome(),
                     requestDto.getPhone(),
                     requestDto.getAppdoc_index());

        return id;
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
        SignStatus signStatus = signStatusRepository.findByUid(tmp_uid).orElse(SignStatus.builder().uid(tmp_uid).sign_status(false).build());

        if (users.getPwd().equals(requestDto.getPwd())) {
            signStatusRepository.delete(signStatus);
            signStatusRepository.save(signStatus.builder().uid(tmp_uid).sign_status(true).build());
            CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "로그인 성공");
            return responeseDto;
        } else {
            CommonResponeseDto responeseDto = new CommonResponeseDto("FAIL", "로그인 실패");
            return responeseDto;
        }
    }

    @Transactional
    public CommonResponeseDto signout(String uid) {

        SignStatus signStatus = signStatusRepository.findByUid(uid).orElse(SignStatus.builder().uid(uid).sign_status(true).build());

        if (signStatus.isSign_status()) {
            signStatus.update(false);

            CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "로그아웃 성공");
            return responeseDto;
        } else {
            CommonResponeseDto responeseDto = new CommonResponeseDto("FAIL", "로그아웃 실패");
            return responeseDto;
        }
    }

    @Transactional
    public CommonResponeseDto dropUser(String uid) {
        Users users = usersRepository.findByUid(uid).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        usersRepository.delete(users);

        CommonResponeseDto responeseDto = new CommonResponeseDto("SUCCESS", "회원탈퇴 성공");
        return responeseDto;
    }

    @Transactional
    public SignStatusResponseDto getStatus(String uid) {
        SignStatus signStatus = signStatusRepository.findByUid(uid).orElse(SignStatus.builder().uid(uid).sign_status(false).build());

        if (signStatus.isSign_status()) {
            SignStatusResponseDto signStatusResponseDto = new SignStatusResponseDto(uid, true);
            return signStatusResponseDto;
        } else {
            SignStatusResponseDto signStatusResponseDto = new SignStatusResponseDto(uid, false);
            return signStatusResponseDto;
        }
    }


    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UsersResponseDto(entity);
    }
}
