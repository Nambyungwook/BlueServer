package com.nbw.blue.springboot.controller;

import com.nbw.blue.springboot.controller.dto.request.UserSavedSitesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersSigninRequestDto;
import com.nbw.blue.springboot.controller.dto.response.*;
import com.nbw.blue.springboot.controller.dto.request.UsersSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.UsersUpdateRequestDto;
import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.domain.sites.SitesRepository;
import com.nbw.blue.springboot.domain.sites.UserSavedSitesRepository;
import com.nbw.blue.springboot.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
public class UsersApiController {

    private final UsersService usersService;
    private final UserSavedSitesRepository userSavedSitesRepository;
    private final SitesRepository sitesRepository;

    //서버확인
    @GetMapping("/blue/v1/check")
    public CommonResponeseDto checkServer() {
        return usersService.checkServer();
    }

    //회원가입
    @PostMapping("/blue/v1/users/signup")
    public UsersResponseDto save(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.save(requestDto);
    }

    //회원정보수정
    @PutMapping("/blue/v1/users/update/{uid}")
    public UsersResponseDto update(@PathVariable String uid, @RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.update(uid, requestDto);
    }

    //회원정보조회
    @GetMapping("/blue/v1/users/userinfo/{uid}")
    public UsersResponseDto findByUid(@PathVariable String uid) {
        return usersService.findByUid(uid);
    }

    //회원정보조회 - index
    @GetMapping("/blue/v1/users/{id}")
    public UsersResponseDto findById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    //회원정보삭제 - index로 삭제
    @DeleteMapping("/blue/v1/users/{id}")
    public Long delete(@PathVariable Long id) {
        usersService.delete(id);

        return id;
    }

    //로그인시 회원정보가 올바르면 true반환
    @PostMapping("/blue/v1/users/signin")
    public CommonResponeseDto signin(@RequestBody UsersSigninRequestDto requestDto) {

        return usersService.signin(requestDto);
    }

    //로그아웃
    @GetMapping("/blue/v1/users/signout/{uid}")
    public CommonResponeseDto signout(@PathVariable String uid) {
        return usersService.signout(uid);
    }

    //회원탈퇴
    @GetMapping("/blue/v1/users/dropout/{uid}")
    public CommonResponeseDto dropout(@PathVariable String uid) {

        return usersService.dropUser(uid);
    }

    //로그인상태 조회
    @GetMapping("/blue/v1/users/status/{uid}")
    public SignStatusResponseDto getStatus(@PathVariable String uid) {
        return usersService.getStatus(uid);
    }

    //사용자가 사이트 저장
    @PostMapping("/blue/v1/users/save/sites/")
    public UserSavedSitesResponseDto saveSites(@RequestBody UserSavedSitesSaveRequestDto requestDto) {
        return usersService.saveSites(requestDto);
    }

    //사용자가 저장한 사이트 목록 조회
    @GetMapping("/blue/v1/users/sites/{uid}")
    public UserSavedSitesListResponseDto getUserSites(@PathVariable String uid) {

        int size = userSavedSitesRepository.findByUid(uid).size();

        ArrayList<Sites> retSitesList = new ArrayList<>();

        for (int i=0; i<size; i ++) {
            Long siteId = userSavedSitesRepository.findByUid(uid).get(i).getSiteId();
            if (sitesRepository.findById(siteId).isPresent()) {
                retSitesList.add(sitesRepository.findById(userSavedSitesRepository.findByUid(uid).get(i).getSiteId()).orElseThrow(()->new IllegalArgumentException("해당 사이트가 없습니다. siteId=" + siteId)));
            } else {
                retSitesList.add(Sites.builder()
                        .siteName("삭제된 사이트")
                        .siteDetail("이 사이트는 존재 하지 않습니다.")
                        .siteUrl("ERROR").build());
            }

        }

        return new UserSavedSitesListResponseDto("SUCCESS", retSitesList);
    }

    //저장한 사이트 삭제
    @GetMapping("/blue/v1/users/delete/site/{uid}/{siteId}")
    public CommonResponeseDto deleteSites(@PathVariable String uid, @PathVariable Long siteId) {

        return usersService.deleteSites(uid, siteId);
    }
}
