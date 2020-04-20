package com.nbw.blue.springboot.service.boards;

import com.nbw.blue.springboot.controller.dto.request.NoticesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.NoticesUpdateRequestDto;
import com.nbw.blue.springboot.controller.dto.response.NoticeListsResponseDto;
import com.nbw.blue.springboot.controller.dto.response.NoticesResponseDto;
import com.nbw.blue.springboot.domain.boards.Notices;
import com.nbw.blue.springboot.domain.boards.NoticesRepository;
import com.nbw.blue.springboot.error.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NoticesService {

    private final NoticesRepository noticesRepository;

    @Transactional(readOnly = true)
    public NoticeListsResponseDto findAll() {

        if (noticesRepository.findAll().size()==0) {
            return new NoticeListsResponseDto("FAIL", ResponseCode.NO_DATA.getMessage(), noticesRepository.findAll());
        }

        return new NoticeListsResponseDto("SUCCESS", ResponseCode.SUCCESS.getMessage(), noticesRepository.findAll());
    }

    @Transactional
    public NoticesResponseDto save(NoticesSaveRequestDto requestDto) {
        return new NoticesResponseDto(noticesRepository.save(requestDto.toEntity()));
    }

    @Transactional
    public NoticesResponseDto update(Long id, NoticesUpdateRequestDto requestDto) {
        Notices notices = noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));
        notices.update(requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getAuthor());

        return new NoticesResponseDto(notices);
    }

    @Transactional
    public void delete(Long id) {
        Notices notices = noticesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 공지가 없습니다. id=" + id));

        noticesRepository.delete(notices);
    }
}
