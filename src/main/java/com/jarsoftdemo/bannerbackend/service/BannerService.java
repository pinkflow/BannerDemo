package com.jarsoftdemo.bannerbackend.service;

import com.jarsoftdemo.bannerbackend.dto.BannerDto;
import com.jarsoftdemo.bannerbackend.entity.Banner;

import java.util.List;

public interface BannerService {

    Banner updateBanner(Banner banner);

    Banner updateBannerFromDto(BannerDto bannerDto);

    void deactivateBanner(Banner banner);

    List<Banner> getAll();

    Banner getBannerById(Long id);

    Banner getBannerForBid(List<String> categoryNameList, String ipAddress, String userAgent);

    List<Banner> getBannersByNamePart(String namePart);

}
