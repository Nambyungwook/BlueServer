package com.nbw.blue.springboot.service.users;

import com.nbw.blue.springboot.controller.dto.UsersResponseDto;
import com.nbw.blue.springboot.controller.dto.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.UsersUpdateRequestDto;
import com.nbw.blue.springboot.domain.users.Users;
import com.nbw.blue.springboot.domain.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public UsersResponseDto findById(Long id) {
        Users entity = usersRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UsersResponseDto(entity);
    }
}
