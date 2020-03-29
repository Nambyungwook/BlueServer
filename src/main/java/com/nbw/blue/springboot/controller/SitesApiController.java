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

    //전체 목록 조회 - 후에 사이트 데이터가 방대해 지면 불러올때 나눠서 불러오도록 수정해야 한다. 그것을 위해 page, size 변수를 만들었다.
    @GetMapping("/blue/v1/sites/")
    public SitesListResponseDto getSites(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(required = false) String targetMain,
                                         @RequestParam(required = false) String targetDetail,
                                         @RequestParam(required = false) String local,
                                         @RequestParam(required = false) String income,
                                         @RequestParam(required = false) String age,
                                         @RequestParam(required = false) String gender,
                                         @RequestParam(required = false) String siteName) {

        return new SitesListResponseDto("SUCCESS",
                sitesRepository.findAllByTargetMainContainingAndTargetDetailContainingAndLocalContainingAndIncomeContainingAndAgeContainingAndGenderContainingAndSiteNameContainingOrderById(targetMain, targetDetail, local, income, age, gender,siteName));
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
