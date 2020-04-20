package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.NoticesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.NoticesUpdateRequestDto;
import com.nbw.blue.springboot.controller.dto.response.NoticeListsResponseDto;
import com.nbw.blue.springboot.controller.dto.response.NoticesResponseDto;
import com.nbw.blue.springboot.service.boards.NoticesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardsApiController {

    private final NoticesService noticesService;

    //공지사항 전체 리스트 보여주기
    @GetMapping("/blue/v1/boards/notice")
    public NoticeListsResponseDto showNoitice() {
        return noticesService.findAll();
    }

    //공지사항 저장하기
    @PostMapping("/blue/v1/boards/notice/save")
    public NoticesResponseDto saveNoitice(@RequestBody NoticesSaveRequestDto requestDto) {
        return noticesService.save(requestDto);
    }

    //공지사항 수정
    @PutMapping("/blue/v1/boards/notice/update/{id}")
    public NoticesResponseDto update(@PathVariable Long id, @RequestBody NoticesUpdateRequestDto requestDto) {
        return noticesService.update(id, requestDto);
    }

    //사이트삭제 - index로 삭제
    @DeleteMapping("/blue/v1/boards/notice/delete/{id}")
    public Long delete(@PathVariable Long id) {
        noticesService.delete(id);

        return id;
    }
}
