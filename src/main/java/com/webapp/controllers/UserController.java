package com.webapp.controllers;


import com.webapp.dto.UserDto;
import com.webapp.service.Interfaces.ActivityService;
import com.webapp.service.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;


/**
 * Created by sednor-5 on 3/22/17.
 */
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private ActivityService activityService;


    @GetMapping("/allUsers")
    public ResponseEntity getUsers() {
        return new ResponseEntity<>(userService.findAll(), OK);
    }

    @GetMapping
    public ResponseEntity getActiveUsers() {
        return new ResponseEntity<>(userService.findAllActiveUsers(), OK);
    }


    @GetMapping("/activity")
    public ResponseEntity getActivities() {
        return new ResponseEntity<>(activityService.findAll(), OK);
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity getSingleUserActivities(@PathVariable("id") Long id) {
        return new ResponseEntity<>(activityService.findOne(id), OK);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.save(userDto), OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") Long id) {

        UserDto userDto = userService.findOne(id);
        return new ResponseEntity<>(userDto == null ? BAD_REQUEST : OK);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UserDto userDto) {
        userService.update(userDto);
        return new ResponseEntity(OK);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity elementNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }
}
