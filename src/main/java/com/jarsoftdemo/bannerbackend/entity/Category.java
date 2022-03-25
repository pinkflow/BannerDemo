package com.jarsoftdemo.bannerbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Category")
public class Category extends BaseEntity {

    @NotEmpty
    @Column(name = "Name", unique = true)
    @Getter
    @Setter
    private String name;

    @NotEmpty
    @Column(name = "Request_id", unique = true)
    @Getter
    @Setter
    private String requestId;

    @ManyToMany(mappedBy = "categories")
    @Getter
    @Setter
    @JsonIgnore
    private Set<Banner> banners = new HashSet<>();

}
