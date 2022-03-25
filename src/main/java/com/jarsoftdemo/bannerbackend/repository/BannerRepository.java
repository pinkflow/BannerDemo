package com.jarsoftdemo.bannerbackend.repository;

import com.jarsoftdemo.bannerbackend.entity.Banner;
import com.jarsoftdemo.bannerbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Long> {

    boolean existsBannerByName(String name);

    @Query(value = "select b from Banner b join b.categories c " +
            "where c in :categories group by b.id having count(b.id) = :categoriesCount order by b.price desc")
    List<Banner> findBidBanner(@Param("categories") Collection<Category> categories, @Param("categoriesCount") Long categoriesCount);

    List<Banner> findByNameContainsIgnoreCase(String namePart);

}
