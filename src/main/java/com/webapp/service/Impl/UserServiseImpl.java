package com.webapp.service.Impl;

import com.webapp.dto.ActivityDto;
import com.webapp.dto.UserDto;
import com.webapp.dao.UserDao;
import com.webapp.entity.User;
import com.webapp.service.Interfaces.ActivityService;
import com.webapp.service.Interfaces.PostService;
import com.webapp.service.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sednor-5 on 3/23/17.
 */
@Service
public class UserServiseImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    protected ActivityService activityService;

    @Autowired
    private PostService postService;


    @Override
    public Long save(UserDto userDto) {
        User user = new User();
        convert(userDto, user);
        user = userDao.save(user);

//Write in Activity new User

        String status = "new User" + user.getUserName();
        saveActivity(user, status);

        return user.getId();

    }

    @Override
    public List<UserDto> findAll() {

        List<User> users = userDao.findAll();
        return users.stream().map(user -> {
            UserDto userDto = new UserDto();
            convert(user, userDto);
            //
            userDto.setPosts(postService.findUserPosts(userDto.getId()));
            return userDto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDto findOne(Long id) {
        UserDto userDto = new UserDto();
        User user = userDao.findOne(id);
        if (user.isStatus()) {
            convert(user, userDto);
            userDto.setPosts(postService.findUserPosts(userDto.getId()));
            return userDto;
        }
        return null;
    }

    @Override
    public void update(UserDto userDto) {
        User user = userDao.findOne(userDto.getId());
        if (user == null) {
            return;
        }
        convert(userDto, user);
        userDao.update(user);
        String status = String.format("user with id  %s was update", user.getId());
        saveActivity(user, status);
    }

    @Override
    public void delete(Long id) {
        User user = userDao.findOne(id);
        if (user == null) {
            return;
        }


        userDao.delete(id);

        String status = String.format("user with id  %s was delete", id);
        saveActivity(user, status);
    }

    @Override
    public List<UserDto> findAllActiveUsers() {
        List<User> users = userDao.findAllActiveUsers();
        return users.stream().map(user -> {
            UserDto userDto = new UserDto();
            convert(user, userDto);
            //
            userDto.setPosts(postService.findUserPosts(userDto.getId()));
            return userDto;
        }).collect(Collectors.toList());
    }

    //    private void convert(User user, UserDto userDto) {
//        userDto.setUserName(user.getUserName());
//        userDto.setId(user.getId());
//        userDto.setPassword(user.getPassword());
//    }
    private void convert(User user, UserDto userDto) {
        userDto.setUserName(user.getUserName());
        userDto.setId(user.getId());
    }

    private void convert(UserDto userDto, User user) {
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setId(userDto.getId());
    }

    private void saveActivity(User user, String status) {


        LocalDateTime date = LocalDateTime.now();
        ActivityDto activityDto = new ActivityDto();
        activityDto.setDate(date);
        activityDto.setUserId(user.getId());
        activityDto.setStatus(status);
        activityService.save(activityDto);
    }



}