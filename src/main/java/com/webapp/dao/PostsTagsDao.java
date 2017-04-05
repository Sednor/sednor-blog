package com.webapp.dao;

import com.webapp.entity.Post;
import com.webapp.entity.PostsTags;
import com.webapp.entity.Tag;

import java.util.List;

/**
 * Created by sednor-5 on 3/28/17.
 */
public interface PostsTagsDao {

    List<Post> getPosts(Long id);
    List<Tag> getTags(Long id);
    PostsTags save(PostsTags postsTags);
    void delete(Long id);
    void deleteWithPostId(Long postId);
    void deleteWithTagId(Long tagId);


}
