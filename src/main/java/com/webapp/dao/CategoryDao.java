package com.webapp.dao;

import com.webapp.entity.Category;

import java.util.List;

/**
 * Created by sednor-5 on 3/24/17.
 */
public interface CategoryDao {

    List<Category> findAll();
    List<Category> findAllActive();
    Category findOne(Long id);
    void save(Category category);
    void delete(Long id);


}
