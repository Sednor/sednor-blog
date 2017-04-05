package com.webapp.controllers;

import com.webapp.dto.CategoryDto;
import com.webapp.service.Interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by sednor-5 on 3/24/17.
 */

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity getActiveCategory(){
        return new ResponseEntity<>(categoryService.findAllActive(), HttpStatus.OK);
    }

    @GetMapping("/allCategories")
    public ResponseEntity getCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addCategory(@RequestBody CategoryDto categoryDto){
        categoryService.save(categoryDto);
        return new ResponseEntity( HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable ("id")Long id){
        CategoryDto categoryDto = categoryService.findOne(id);
        return new ResponseEntity(categoryDto,categoryDto == null?HttpStatus.BAD_REQUEST:HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity elementNotFound(Exception e) {
        return new ResponseEntity<>(e.getMessage(), BAD_REQUEST);
    }
}
