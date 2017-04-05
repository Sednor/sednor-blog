package com.webapp.dao;

import com.webapp.entity.Activity;
import com.webapp.entity.User;

import java.util.List;

/**
 * Created by sednor-5 on 3/23/17.
 */
public interface    ActivityDao {

    Activity save(Activity activity);
    List<Activity> findAll();
    List<Activity> findOne(Long userId);


}
