package com.webapp.service.Impl;

import com.webapp.dto.ActivityDto;
import com.webapp.dao.ActivityDao;
import com.webapp.entity.Activity;
import com.webapp.service.Interfaces.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sednor-5 on 3/23/17.
 */
@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    protected ActivityDao activityDao;


    @Override
    public void save(ActivityDto activityDto) {
        Activity activity = new Activity();
        convert(activityDto, activity);
        activityDao.save(activity);

    }

    @Override
    public List<ActivityDto> findAll() {

        List<Activity> activities = activityDao.findAll();
        return activities.stream().map(activity -> {
            ActivityDto activityDto = new ActivityDto();
            convert(activity, activityDto);
            return activityDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ActivityDto> findOne(Long userId) {

        List<Activity> activities = activityDao.findOne(userId);
        return activities.stream().map(activity -> {
            ActivityDto activityDto = new ActivityDto();
            convert(activity, activityDto);
            return activityDto;
        }).collect(Collectors.toList());
    }


    private void convert(ActivityDto activityDto, Activity activity) {
        //activity.setId(activityDto.getId());
        activity.setDate(activityDto.getDate());
        activity.setStatus(activityDto.getStatus());
        activity.setUserId(activityDto.getUserId());
    }
    private void convert(Activity activity, ActivityDto activityDto) {
        activityDto.setId(activity.getId());
        activityDto.setDate(activity.getDate());
        activityDto.setUserId(activity.getUserId());
        activityDto.setStatus(activity.getStatus());
    }
}
