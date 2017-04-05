package com.webapp.service.Interfaces;

import com.webapp.dto.ActivityDto;


import java.util.List;

/**
 * Created by sednor-5 on 3/23/17.
 */
public interface ActivityService {

    void save(ActivityDto activityDto);
    List<ActivityDto> findAll();
    List<ActivityDto> findOne(Long userId);
}
