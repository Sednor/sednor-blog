package com.webapp.service.Interfaces;

import com.webapp.dto.PostDto;
import com.webapp.dto.TagDto;
import com.webapp.entity.PostsTags;

import java.util.List;

/**
 * Created by sednor-5 on 3/28/17.
 */
public interface PostsTagsService {
    List<PostDto> getPosts(Long id);
    List<TagDto> getTags(Long id);
    void save(PostsTags postsTags);
    void delete(Long id);
    void deleteWithPostId(Long postId);
    void deleteWithTagId(Long tagId);
}
