package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.SitesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.SitesUpdateRequestDto;
import com.nbw.blue.springboot.controller.dto.response.SitesListResponseDto;
import com.nbw.blue.springboot.controller.dto.response.SitesResponseDto;
import com.nbw.blue.springboot.domain.sites.SitesRepository;
import com.nbw.blue.springboot.service.sites.SitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class SitesApiController {

    private final SitesService sitesService;
    private final SitesRepository sitesRepository;

    //전체 목록 조회
    @GetMapping("/blue/v1/sites/")
    public SitesListResponseDto getSites(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(required = false) String categoryB,
                                         @RequestParam(required = false) String categoryM,
                                         @RequestParam(required = false) String categoryS,
                                         @RequestParam(required = false) String siteName) {

        if (siteName != null) {
            return new SitesListResponseDto("SUCCESS", sitesRepository.findBySiteName(siteName));
        } else if (categoryS != null) {
            return new SitesListResponseDto("SUCCESS", sitesRepository.findByCategoryS(categoryS));
        } else if (categoryM != null) {
            return new SitesListResponseDto("SUCCESS", sitesRepository.findByCategoryM(categoryM));
        } else if (categoryB != null) {
            return new SitesListResponseDto("SUCCESS", sitesRepository.findByCategoryB(categoryB).subList(page, size));
        } else {
            return new SitesListResponseDto("SUCCESS", sitesRepository.findAll( ).subList(page, size));
        }
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
