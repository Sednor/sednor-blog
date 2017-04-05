package com.webapp.dao;

import com.webapp.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sednor-5 on 3/21/17.
 */


public interface UserDao {

    User save(User user);

    List<User> findAll();

    List<User> findAllActiveUsers();

    User findOne(Long id);

    void delete(Long i);

    void update(User user);

    User findByName(String name);

}
