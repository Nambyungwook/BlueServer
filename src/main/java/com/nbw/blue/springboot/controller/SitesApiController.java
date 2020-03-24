package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.SitesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.SitesUpdateRequestDto;
import com.nbw.blue.springboot.controller.dto.response.SitesResponseDto;
import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.service.sites.SitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SitesApiController {

    private final SitesService sitesService;

    //전체 목록 조회
    @GetMapping("/blue/v1/sites/")
    public Page<Sites> getSites(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "10") int size) {

        PageRequest pageable = PageRequest.of(page - 1, size,  Sort.by(Sort.Direction.DESC, "createdDate"));

        Page<Sites> sites = sitesService.findAll(pageable);

        return sites;
    }

    //사이트 추가
    @PostMapping("/blue/v1/sites/save")
    public SitesResponseDto save(@RequestBody SitesSaveRequestDto requestDto) {
        return sitesService.save(requestDto);
    }

    //사이트 정보 수정
    @PutMapping("/blue/v1/sites/update/{id}")
    public SitesResponseDto update(@PathVariable Long id, @RequestBody SitesUpdateRequestDto requestDto) {
        return sitesService.update(id, requestDto);
    }

    //사이트삭제 - index로 삭제
    @DeleteMapping("/blue/v1/sites/delete/{id}")
    public Long delete(@PathVariable Long id) {
        sitesService.delete(id);

        return id;
    }
}
