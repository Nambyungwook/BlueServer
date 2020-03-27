package com.nbw.blue.springboot.service.sites;

import com.nbw.blue.springboot.controller.dto.request.SitesSaveRequestDto;
import com.nbw.blue.springboot.controller.dto.request.SitesUpdateRequestDto;
import com.nbw.blue.springboot.controller.dto.response.SitesResponseDto;
import com.nbw.blue.springboot.domain.sites.Sites;
import com.nbw.blue.springboot.domain.sites.SitesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SitesService {

    private final SitesRepository sitesRepository;

    @Transactional
    public SitesResponseDto save(SitesSaveRequestDto requestDto) {
        return new SitesResponseDto(sitesRepository.save(requestDto.toEntity()));
    }

    @Transactional
    public SitesResponseDto update(Long id, SitesUpdateRequestDto requestDto) {
        Sites sites = sitesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사이트가 없습니다. id=" + id));
        sites.update(requestDto.getSiteName(),
                requestDto.getCategoryB(),
                requestDto.getCategoryM(),
                requestDto.getCategoryS(),
                requestDto.getSiteUrl(),
                requestDto.getSiteDetail());

        return new SitesResponseDto(sites);
    }

    @Transactional
    public void delete(Long id) {
        Sites sites = sitesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사이트가 없습니다. id=" + id));

        sitesRepository.delete(sites);
    }
}
