package com.nbw.blue.springboot.service.users;

import com.nbw.blue.springboot.controller.dto.response.UsersResponseDto;
import com.nbw.blue.springboot.controller.dto.request.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersSigninRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersUpdateRequestDto;
import com.nbw.blue.springboot.domain.users.SignToken;
import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.domain.users.UsersRepository;
import com.nbw.blue.springboot.error.ApplicationException;
import com.nbw.blue.springboot.error.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

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
    public void signin(UsersSigninRequestDto requestDto) {
        Optional<Users> optionalUser = usersRepository.findByEmail(requestDto.getEmail());

        if (!optionalUser.isPresent()) {
            throw new ApplicationException(ResponseCode.NOT_EXIST_USER);
        }

        Users users = optionalUser.get();

        if (!users.getPwd().equals(requestDto.getPwd())) {
            throw new ApplicationException(ResponseCode.INVALID_PASSWORD);
        }

        SignTokenService signTokenService = new SignTokenService();

        String signToken = signTokenService.make(users.getUid());
        SignToken savedSignToken = signTokenService.updateAuthToken(users.getUid());

        return;
    }

    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UsersResponseDto(entity);
    }
}
