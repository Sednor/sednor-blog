package com.webapp.dao.impl;

import com.webapp.dao.PostDao;
import com.webapp.dao.rowMappers.PostRowMapper;
import com.webapp.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by sednor-5 on 3/27/17.
 */
public class PostDaoImpl implements PostDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Post save(Post post) {
        String sql = "INSERT INTO post(title,description,category_id,user_id) VALUES(?,?,?,?)";
//        int i = jdbcTemplate.update(sql, user.getUserName(), user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, post.getTitle());
                    ps.setString(2, post.getDescription());
                    ps.setLong(3,post.getCategoryId());
                    ps.setLong(4, post.getUserId());
                    return ps;
                },
                keyHolder);
        Long i = keyHolder.getKey().longValue();
        post.setId(i);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("SELECT * FROM post", new PostRowMapper());
    }

    @Override
    public Post findOne(Long id) {
        String sql = "SELECT * FROM post WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},new PostRowMapper());
    }

    @Override
    public void delete(Long id) {

        String sql = "DELETE FROM post WHERE id=?";
       jdbcTemplate.update(sql, id);

    }

    @Override
    public List<Post> findUserPosts(Long idUser) {

        String sql = "SELECT * FROM post WHERE user_id="+ idUser;
        return jdbcTemplate.query(sql, new PostRowMapper());
    }

    @Override
    public List<Post> findCategoryPosts(Long idCategory) {
        String sql = "SELECT * FROM post WHERE category_id="+ idCategory;
        return jdbcTemplate.query(sql, new PostRowMapper());
    }

    @Override
    public void update(Post post) {
        String sql = "UPDATE post SET title=?, description=?, category_id=?, user_id=? WHERE id=?";
        jdbcTemplate.update(sql,post.getTitle(),post.getDescription(),
                post.getCategoryId(),post.getUserId(), post.getId());
    }
}
