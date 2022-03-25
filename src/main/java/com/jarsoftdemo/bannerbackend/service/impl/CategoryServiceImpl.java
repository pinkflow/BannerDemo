package com.jarsoftdemo.bannerbackend.service.impl;

import com.jarsoftdemo.bannerbackend.entity.Category;
import com.jarsoftdemo.bannerbackend.enums.Status;
import com.jarsoftdemo.bannerbackend.exception.DataNotFoundException;
import com.jarsoftdemo.bannerbackend.exception.DeactivationException;
import com.jarsoftdemo.bannerbackend.repository.CategoryRepository;
import com.jarsoftdemo.bannerbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Autowired
    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category updateCategory(Category category) {
        category.setStatus(Status.ACTIVE);
        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public void deactivateCategory(Category category) {
        if (category.getBanners().size() > 0) {
            throw new DeactivationException("Can not delete category with with existing linked banners");
        }
        category.setStatus(Status.DELETED);
        categoryRepository.saveAndFlush(category);
    }

    @Override
    public List<Category> getCategoriesByNamePart(String namePart) {
        return categoryRepository.findByNameContainsIgnoreCase(namePart);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("No category with such id: " + id));
    }
}
