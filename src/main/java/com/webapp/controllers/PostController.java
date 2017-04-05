package com.webapp.controllers;

import com.webapp.dto.PostDto;
import com.webapp.service.Interfaces.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by sednor-5 on 3/27/17.
 */
@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity addPost(@RequestBody PostDto postDto) {
        PostDto savedPost = postService.save(postDto);
        return new ResponseEntity<>(savedPost == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity getPosts(){
        List<PostDto> posts = postService.findAll();
        return new ResponseEntity(posts,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable("id")Long id){
        PostDto postDto = postService.findOne(id);
        return new ResponseEntity(postDto, HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity updatePost(@RequestBody PostDto postDto){
        postService.update(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @GetMapping("/userPosts/{id}")
    public  ResponseEntity getUserPosts(@PathVariable("id")Long id){
        List<PostDto> posts = postService.findUserPosts(id);
        return new ResponseEntity(posts,HttpStatus.OK);
    }
    @GetMapping("/categoryPosts/{id}")
    public  ResponseEntity getCategoryPosts(@PathVariable("id")Long id){
        List<PostDto> posts = postService.findCategoryPosts(id);
        return new ResponseEntity(posts,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletePost(@PathVariable("id")Long id){
        postService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity elementNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }
}
