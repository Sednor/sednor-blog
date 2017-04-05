package com.webapp.dao.impl;

import com.webapp.dao.CategoryDao;
import com.webapp.dao.rowMappers.CategoryRowMapper;
import com.webapp.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by sednor-5 on 3/24/17.
 */
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAll() {

        String sql = "SELECT * FROM category";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    @Override
    public List<Category> findAllActive() {
        String sql = "SELECT * FROM category WHERE status=true";
        return jdbcTemplate.query(sql, new CategoryRowMapper());
    }

    @Override
    public Category findOne(Long id) {
        String sql = "SELECT * FROM category WHERE id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id},new CategoryRowMapper());
    }

    @Override
    public void save(Category category) {
        String sql = "INSERT INTO category (name, user_id, status) VALUES (?,?,?)";
        jdbcTemplate.update(sql,category.getName(),category.getUserId(),true);

    }

    @Override
    public void delete(Long id) {
        String sql = "UPDATE category SET status=FALSE WHERE id=?";
        jdbcTemplate.update(sql, id);

    }
}
