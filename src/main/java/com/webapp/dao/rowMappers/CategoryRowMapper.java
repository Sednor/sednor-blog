package com.webapp.dao.rowMappers;

import com.webapp.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sednor-5 on 3/24/17.
 */
public class CategoryRowMapper implements RowMapper<Category>{
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setName(resultSet.getString("name"));
        category.setUserId(resultSet.getLong("user_id"));
        category.setStatus(resultSet.getBoolean("status"));
        return category;
    }
}
