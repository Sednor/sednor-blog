package com.webapp.service.Interfaces;

import com.webapp.dto.UserDto;

import java.util.List;

/**
 * Created by sednor-5 on 3/23/17.
 */

public interface UserService {
    Long save(UserDto userDto);
    List<UserDto> findAll();
    UserDto findOne(Long id);
    void update(UserDto userDto);
    void delete(Long id);
    List<UserDto> findAllActiveUsers();
}
