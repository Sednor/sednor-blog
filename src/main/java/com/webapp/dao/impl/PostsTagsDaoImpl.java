package com.webapp.dao.impl;

import com.webapp.dao.PostsTagsDao;
import com.webapp.dao.rowMappers.PostRowMapper;
import com.webapp.dao.rowMappers.TagRowMapper;
import com.webapp.entity.Post;
import com.webapp.entity.PostsTags;
import com.webapp.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

/**
 * Created by sednor-5 on 3/28/17.
 */

public class PostsTagsDaoImpl implements PostsTagsDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Post> getPosts(Long id) {

        String sql = "SELECT post.id, post.title, post.description, post.category_id, post.user_id" +
                " FROM post INNER JOIN posts_tags ON post.id=posts_tags.post_id INNER JOIN " +
                "tag ON posts_tags.tags_id=tag.id WHERE tag.id=?";
        return jdbcTemplate.query(sql,new Object[] {id}, new PostRowMapper());
    }


    @Override
    public List<Tag> getTags(Long id) {
        String sql = "SELECT tag.id, tag.name FROM " +
                "tag INNER JOIN posts_tags ON tag.id=posts_tags.tags_id INNER JOIN " +
                "post ON posts_tags.post_id=post.id WHERE post.id=?";
        return jdbcTemplate.query(sql, new Object[]{id}, new TagRowMapper());
    }

    @Override
    public PostsTags save(PostsTags postsTags) {

        String sql = "INSERT INTO posts_tags(tags_id,post_id) VALUES(?,?)";


        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(sql, new String[]{"id"});
                    ps.setLong(1, postsTags.getTagsId());
                    ps.setLong(2, postsTags.getPostId());
                    return ps;
                },
                keyHolder);

        Long i = keyHolder.getKey().longValue();
        postsTags.setId(i);
        return postsTags;

    }

    @Override
    public void delete(Long id) {

        String sql = "DELETE FROM posts_tags WHERE id=?";
        jdbcTemplate.update(sql, id);

    }

    @Override
    public void deleteWithPostId(Long postId) {
        String sql = "DELETE FROM posts_tags WHERE post_id=?";
        jdbcTemplate.update(sql,postId);
    }

    @Override
    public void deleteWithTagId(Long tagId) {
        String sql = "DELETE FROM posts_tags WHERE tags_id=?";
        jdbcTemplate.update(sql,tagId);
    }
}
