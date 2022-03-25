package com.jarsoftdemo.bannerbackend.entity;

import com.jarsoftdemo.bannerbackend.enums.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Banner")
@Data
public class Banner extends BaseEntity {

    @NotEmpty(message = "Name is mandatory")
    @Column(name = "Name", unique = true)
    private String name;

    @NotNull(message = "price is mandatory")
    @Column(name = "Price")
    private BigDecimal price;

    @NotEmpty(message = "Text is mandatory")
    @Column(name = "Text", columnDefinition = "text")
    private String text;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "banner_category",
            joinColumns = {@JoinColumn(name = "banner_id")},
            inverseJoinColumns = {@JoinColumn(name = "category_id")}
    )
    Set<Category> categories = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status;

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getBanners().add(this);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
        category.getBanners().remove(this);
    }

}
