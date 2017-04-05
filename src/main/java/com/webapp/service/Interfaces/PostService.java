package com.webapp.service.Interfaces;

import com.webapp.dto.PostDto;

import java.util.List;

/**
 * Created by sednor-5 on 3/27/17.
 */
public interface PostService {

    PostDto save(PostDto postDto);
    List<PostDto> findAll();
    PostDto findOne(Long id);
    void delete(Long id);
    List<PostDto> findUserPosts(Long IdUser);
    List<PostDto> findCategoryPosts(Long IdCategory);
    void update(PostDto postDto);
}
