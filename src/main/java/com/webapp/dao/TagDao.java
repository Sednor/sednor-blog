package com.webapp.dao;

import com.webapp.entity.Tag;

import java.util.List;

/**
 * Created by sednor-5 on 3/27/17.
 */
public interface TagDao {

    Tag save(Tag tag);
    List<Tag> findAll();
    void delete(Long id);
    void update(Tag tag);
    Tag findOne(Long id);


}
