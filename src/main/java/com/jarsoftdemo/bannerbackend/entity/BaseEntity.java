package com.jarsoftdemo.bannerbackend.entity;


import com.jarsoftdemo.bannerbackend.enums.Status;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name="Status")
    private Status status;

}
