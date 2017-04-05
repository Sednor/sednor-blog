package com.webapp.controllers;

import com.webapp.dto.TagDto;
import com.webapp.service.Interfaces.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by sednor-5 on 3/28/17.
 */

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService tagsService;


    @GetMapping
    public ResponseEntity getTags() {
        List<TagDto> allTags = tagsService.findAll();
        return new ResponseEntity(allTags, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addTag(@RequestBody TagDto tagDto) {
        TagDto tag = tagsService.save(tagDto);
        return new ResponseEntity(tag == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id) {
        TagDto tagDto = tagsService.findOne(id);
        return new ResponseEntity(tagDto, tagDto == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        tagsService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity elementNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }


}


