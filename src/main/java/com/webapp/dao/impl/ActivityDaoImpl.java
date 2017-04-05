package com.webapp.dao.impl;

import com.webapp.dao.ActivityDao;
import com.webapp.dao.rowMappers.ActivityRowMapper;
import com.webapp.entity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by sednor-5 on 3/23/17.
 */
public class ActivityDaoImpl implements ActivityDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;


    @Override
    public Activity save(Activity activity) {
        String sql = "INSERT INTO activity (status, date, user_id) VALUES (?,?,?);";
        jdbcTemplate.update(sql, activity.getStatus(), Timestamp.valueOf(activity.getDate()), activity.getUserId());
        return activity;
    }

    @Override
    public List<Activity> findAll() {
        return jdbcTemplate.query("SELECT * FROM activity", new ActivityRowMapper());
    }

    @Override
    public List<Activity> findOne(Long userId) {
        return jdbcTemplate.query("SELECT * FROM activity WHERE user_id=" + userId, new ActivityRowMapper());
    }


}
