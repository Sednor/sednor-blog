package com.webapp.dao.rowMappers;

import com.webapp.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by sednor-5 on 3/28/17.
 */
public class TagRowMapper implements RowMapper<Tag> {
    @Override
    public Tag mapRow(ResultSet resultSet, int i) throws SQLException {

        Tag tag = new Tag();
        tag.setId(resultSet.getLong("id"));
        tag.setName(resultSet.getString("name"));
        return tag;
    }
}
