package com.webapp.dao.rowMappers;

import com.webapp.entity.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sednor-5 on 3/27/17.
 */
public class PostRowMapper implements RowMapper<Post> {


    @Override
    public Post mapRow(ResultSet resultSet, int i) throws SQLException {

        Post post = new Post();
        post.setId(resultSet.getLong("id"));
        post.setDescription(resultSet.getString("description"));
        post.setTitle(resultSet.getString("title"));
        post.setCategoryId(resultSet.getLong("category_id"));
        post.setUserId(resultSet.getLong("user_id"));

        return post;
    }
}
