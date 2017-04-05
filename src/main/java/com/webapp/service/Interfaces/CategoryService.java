package com.webapp.service.Interfaces;

import com.webapp.dto.CategoryDto;

import java.util.List;

/**
 * Created by sednor-5 on 3/24/17.
 */
public interface CategoryService {

    List<CategoryDto> findAll();
    List<CategoryDto> findAllActive();
    CategoryDto findOne(Long id);
    void save(CategoryDto categoryDto);
    void delete(Long id);

}
