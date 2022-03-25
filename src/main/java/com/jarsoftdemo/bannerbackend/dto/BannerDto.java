package com.jarsoftdemo.bannerbackend.dto;

import com.jarsoftdemo.bannerbackend.enums.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class BannerDto {


    public Long id;
    public String name;
    public BigDecimal price;
    public String text;
    public Set<Long> categoriesIdList;
    public Status status;

}
