package com.webapp.dao.impl;

import com.webapp.dao.TagDao;
import com.webapp.dao.rowMappers.TagRowMapper;
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

public class TagDaoImpl implements TagDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Tag save(Tag tag) {
        String sql = "INSERT INTO tag (name) VALUES (?)";
        //return jdbcTemplate.update(sql, tag.getName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, tag.getName());
                    return ps;
                },
                keyHolder);
        Long i = keyHolder.getKey().longValue();
        tag.setId(i);
        return tag;

    }

    @Override
    public List<Tag> findAll() {
        return jdbcTemplate.query("SELECT * FROM tag",new TagRowMapper());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM tag WHERE id=?",id);
    }

    @Override
    public void update(Tag tag) {
        String sql = "UPDATE tag SET name=? WHERE id=?";
        jdbcTemplate.update(sql, tag.getName(), tag.getId());
    }

    @Override
    public Tag findOne(Long id) {
        String sql = "SELECT * FROM tag WHERE id=?";
        return jdbcTemplate.queryForObject(sql,new Object[]{id}, new TagRowMapper());
    }
}
