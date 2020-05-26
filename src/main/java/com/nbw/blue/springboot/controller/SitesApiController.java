package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.SitesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.SitesUpdateRequestDto;
import com.nbw.blue.springboot.controller.dto.response.SitesListResponseDto;
import com.nbw.blue.springboot.controller.dto.response.SitesResponseDto;
import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.domain.sites.SitesRepository;
import com.nbw.blue.springboot.service.sites.SitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
                                         @RequestParam(required = false) String subLocal,
                                         @RequestParam(required = false) String allLocal,
                                         @RequestParam(required = false) String income,
                                         @RequestParam(required = false) String allIncome,
                                         @RequestParam(required = false) String age,
                                         @RequestParam(required = false) String allAge,
                                         @RequestParam(required = false) String gender,
                                         @RequestParam(required = false) String allGender,
                                         @RequestParam(required = false) String siteName) {

        String input_all_local = local;
        String input_all_sub_local = subLocal;
        String input_all_income = income;
        String input_all_age = age;
        String input_all_gender = gender;

        if (allLocal.equals("1")) {
            input_all_local = "무관";
            input_all_sub_local = "무관";
        }

        if (allIncome.equals("1")) {
            input_all_income = "무관";
        }

        if (allAge.equals("1")) {
            input_all_age = "무관";
        }

        if (allGender.equals("1")) {
            input_all_gender = "무관";
        }

        List<Sites> searchedSitesList = sitesRepository.findAllByTargetMainContainingAndTargetDetailContainingAndLocalContainingAndSubLocalContainingAndIncomeContainingAndAgeContainingAndGenderContainingAndSiteNameContainingOrderById(targetMain, targetDetail, local, subLocal, income, age, gender, siteName);
        List<Sites> allCheckSitesList = sitesRepository.findAllByTargetMainContainingAndTargetDetailContainingAndLocalContainingAndSubLocalContainingAndIncomeContainingAndAgeContainingAndGenderContainingAndSiteNameContainingOrderById(targetMain, targetDetail, input_all_local, input_all_sub_local, input_all_income, input_all_age, input_all_gender, siteName);

        List<Sites> retSitesList = new ArrayList<>();
        retSitesList.addAll(allCheckSitesList);

        //중복 제거 로직
        for(Sites site : searchedSitesList){
            if(!retSitesList.contains(site))
                retSitesList.add(site);
        }
        //사이트 이름 순서로 정렬(큰순서)
        retSitesList.sort(Sites::compareTo);

        return new SitesListResponseDto("SUCCESS", retSitesList);
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
