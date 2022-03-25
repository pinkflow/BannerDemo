package com.jarsoftdemo.bannerbackend.controller;

import com.jarsoftdemo.bannerbackend.dto.BannerDto;
import com.jarsoftdemo.bannerbackend.entity.Banner;
import com.jarsoftdemo.bannerbackend.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
public class BannerController {

    private final BannerService bannerService;

    @Autowired
    BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/bid")
    public Banner getBanner(@RequestParam Map<String, String> categoryMap, HttpServletRequest request) {
        List<String> categoryList = new ArrayList<>(categoryMap.values());
        return bannerService.getBannerForBid(categoryList, request.getRemoteAddr(), request.getHeader("User-Agent"));
    }

    @GetMapping("/api/banner")
    public List<Banner> getBanners(@RequestParam(required = false) String namePart) {
        if (namePart != null) {
            return bannerService.getBannersByNamePart(namePart);
        }
        return bannerService.getAll();
    }

    @GetMapping("/api/banner/{id}")
    public ResponseEntity<Banner> getBanner(@PathVariable Long id) {
        Banner banner = bannerService.getBannerById(id);
        return ResponseEntity.ok(banner);
    }

    @PutMapping("/api/banner/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable Long id, @Valid @RequestBody BannerDto updatedBannerDto) {
        Banner banner = bannerService.getBannerById(id);
        updatedBannerDto.setId(banner.getId());
        Banner updatedBanner = bannerService.updateBannerFromDto(updatedBannerDto);
        return ResponseEntity.ok(updatedBanner);

    }

    @PutMapping("/api/banner")
    public ResponseEntity<Banner> createBanner(@Valid @RequestBody BannerDto updatedBannerDto) {
        Banner updatedBanner = bannerService.updateBannerFromDto(updatedBannerDto);
        return ResponseEntity.ok(updatedBanner);

    }

    @DeleteMapping("/api/banner/{id}")
    public ResponseEntity<Banner> deleteBanner(@PathVariable Long id){
        Banner banner = bannerService.getBannerById(id);
        bannerService.deactivateBanner(banner);
        return ResponseEntity.ok(banner);
    }

}
