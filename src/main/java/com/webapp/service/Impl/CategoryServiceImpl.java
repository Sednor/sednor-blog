package com.webapp.service.Impl;

import com.webapp.dto.CategoryDto;
import com.webapp.dao.CategoryDao;
import com.webapp.entity.Category;
import com.webapp.service.Interfaces.CategoryService;
import com.webapp.service.Interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sednor-5 on 3/24/17.
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;
    @Autowired
    PostService postService;

    @Override
    public List<CategoryDto> findAll() {

        List <Category> categories = categoryDao.findAll();
        return categories.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            convert(category,categoryDto);
            //
            categoryDto.setPosts(postService.findCategoryPosts(categoryDto.getId()));
            return categoryDto;
        }).collect(Collectors.toList());

    }

    @Override
    public List<CategoryDto> findAllActive() {
        List <Category> categories = categoryDao.findAllActive();
        return categories.stream().map(category -> {
            CategoryDto categoryDto = new CategoryDto();
            convert(category,categoryDto);

            categoryDto.setPosts(postService.findCategoryPosts(categoryDto.getId()));
            return categoryDto;
        }).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findOne(Long id) {

        Category category = categoryDao.findOne(id);
        CategoryDto categoryDto = new CategoryDto();
        if(category.getStatus()){
            convert(category, categoryDto);
            categoryDto.setPosts(postService.findCategoryPosts(categoryDto.getId()));
            return categoryDto;
        }
        return null;

    }

    @Override
    public void save(CategoryDto categoryDto) {

        Category category = new Category();
        convert(categoryDto,category);
        categoryDao.save(category);

    }

    @Override
    public void delete(Long id) {
        categoryDao.delete(id);
    }

    private void convert(Category category, CategoryDto categoryDto){
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setUserId(category.getUserId());
    }
    private void convert(CategoryDto categoryDto, Category category){
        category.setName(categoryDto.getName());
        category.setUserId(categoryDto.getUserId());
    }


}
