package com.webapp.dao.impl;

import com.webapp.dao.UserDao;
import com.webapp.dao.rowMappers.UserRowMapper;
import com.webapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;


public class UserDaoImpl implements UserDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users(user_name,password,status) VALUES(?,?,?)";
//        int i = jdbcTemplate.update(sql, user.getUserName(), user.getPassword());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
                    PreparedStatement ps =
                            connection.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getPassword());
                    ps.setBoolean(3, true);
                    return ps;
                },
                keyHolder);
        Long i = keyHolder.getKey().longValue();
        user.setId(i);
        return user;
    }


    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    public List<User> findAllActiveUsers() {
        return jdbcTemplate.query("SELECT * FROM users WHERE status=TRUE ", new UserRowMapper());
    }

    @Override
    public User findOne(Long id){
        String sql = "SELECT * FROM users  WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new UserRowMapper());
    }

    @Override
    public void delete(Long i) {

//        String sql = "DELETE FROM users WHERE id=?";
//        jdbcTemplate.update(sql, i);
        String sql = "UPDATE users SET status=FALSE WHERE id=?";
        jdbcTemplate.update(sql, i);


    }

    @Override
    public void update(User user) {

        String sql = "UPDATE users SET user_name=?, password=? WHERE id=?";
        jdbcTemplate.update(sql, user.getUserName(), user.getPassword(), user.getId());

    }

    @Override
    public User findByName(String name) {
        String sql = "SELECT * FROM users  WHERE user_name=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{name}, new UserRowMapper());
    }
}
