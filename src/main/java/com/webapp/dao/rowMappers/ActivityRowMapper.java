package com.webapp.dao.rowMappers;


import com.webapp.entity.Activity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by sednor-5 on 3/23/17.
 */
public class ActivityRowMapper implements RowMapper<Activity>{
    @Override
    public Activity mapRow(ResultSet resultSet, int i) throws SQLException {


        Activity activity = new Activity();
        activity.setId(resultSet.getLong("id"));
//        activity.setDate(resultSet.getDate("date"));
//       activity.setDate(resultSet.getTimestamp("date").toLocalDateTime());
//        LocalDateTime.ofInstant(Instant.ofEpochSecond(new Date(), ZoneId.systemDefault());
        activity.setDate(resultSet.getTimestamp("date").toLocalDateTime());

        activity.setStatus(resultSet.getString("status"));
        activity.setUserId(resultSet.getLong("user_id"));
        return activity;
    }
}
