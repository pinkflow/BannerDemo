package com.jarsoftdemo.bannerbackend.service.impl;

import com.jarsoftdemo.bannerbackend.dto.BannerDto;
import com.jarsoftdemo.bannerbackend.entity.Banner;
import com.jarsoftdemo.bannerbackend.entity.Category;
import com.jarsoftdemo.bannerbackend.entity.LogRecord;
import com.jarsoftdemo.bannerbackend.enums.Status;
import com.jarsoftdemo.bannerbackend.exception.DataNotFoundException;
import com.jarsoftdemo.bannerbackend.exception.NoContentException;
import com.jarsoftdemo.bannerbackend.exception.ValidationException;
import com.jarsoftdemo.bannerbackend.repository.BannerRepository;
import com.jarsoftdemo.bannerbackend.repository.CategoryRepository;
import com.jarsoftdemo.bannerbackend.service.BannerService;
import com.jarsoftdemo.bannerbackend.service.LogRecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BannerServiceImpl implements BannerService {


    private final BannerRepository bannerRepository;


    private final CategoryRepository categoryRepository;
    private final LogRecordService logRecordService;

    private final ModelMapper modelMapper;

    @Autowired
    BannerServiceImpl(BannerRepository bannerRepository, CategoryRepository categoryRepository, LogRecordService logRecordService, ModelMapper modelMapper) {
        this.bannerRepository = bannerRepository;
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.logRecordService = logRecordService;
    }

    @Override
    public Banner updateBanner(Banner banner) {
        try {
            banner.setStatus(Status.ACTIVE);
            return bannerRepository.saveAndFlush(banner);
        } catch (Exception exception) {
            throw new ValidationException("Invalid data");
        }
    }

    @Override
    public Banner updateBannerFromDto(BannerDto bannerDto) {
        Banner banner = convertToEntity(bannerDto);
        return updateBanner(banner);
    }

    @Override
    public void deactivateBanner(Banner banner) {
        banner.setStatus(Status.DELETED);
        bannerRepository.saveAndFlush(banner);
    }


    @Override
    public List<Banner> getAll() {
        return bannerRepository.findAll();
    }

    @Override
    public List<Banner> getBannersByNamePart(String namePart) {
        return bannerRepository.findByNameContainsIgnoreCase(namePart);
    }

    @Override
    public Banner getBannerById(Long id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No banner with such id: " + id));
    }

    public Banner getBannerForBid(List<String> categoryNameList, String ipAddress, String userAgent) {
        Set<Category> categorySet = new HashSet<>(categoryRepository.findAllByNameIn(categoryNameList));
        if (categorySet.size() != categoryNameList.size()) {
            throw new NoContentException("No banners found with given categories");
        }
        Set<Banner> shownBanners = logRecordService.getTodayRecords(ipAddress, userAgent)
                .stream().map(LogRecord::getBanner).collect(Collectors.toSet());
        List<Banner> banners = bannerRepository.findBidBanner(categorySet, (long) categorySet.size());
        if (banners.size() == 0) {
            throw new NoContentException("No banners found with given categories");
        }
        banners = banners.stream().filter((banner -> !shownBanners.contains(banner))).collect(Collectors.toList());
        try {
            return banners.get(0);
        } catch (IndexOutOfBoundsException ignored) {
            throw new NoContentException("This user has seen all the banners with given categories");
        }
    }

    private BannerDto convertToDto(Banner banner) {
        BannerDto bannerDto = modelMapper.map(banner, BannerDto.class);
        bannerDto.categoriesIdList = banner.getCategories().stream().map((Category::getId)).collect(Collectors.toSet());
        return bannerDto;
    }

    private Banner convertToEntity(BannerDto bannerDto) {
        Banner banner = modelMapper.map(bannerDto, Banner.class);
        Set<Category> categorySet = new HashSet<>(categoryRepository.findAllByIdIn(bannerDto.categoriesIdList));
        banner.setCategories(categorySet);
        return banner;
    }

}
