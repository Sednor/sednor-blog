package com.webapp.dao;

import com.webapp.entity.Post;

import java.util.List;

/**
 * Created by sednor-5 on 3/27/17.
 */
public interface PostDao {

    Post save(Post post);
    List<Post> findAll();
    Post findOne(Long id);
    void delete(Long id);
    List<Post> findUserPosts(Long idUser);
    List<Post> findCategoryPosts(Long idCategory);
    void update(Post post);


}
