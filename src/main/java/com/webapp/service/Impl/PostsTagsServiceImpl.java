package com.webapp.service.Impl;

import com.webapp.dto.PostDto;
import com.webapp.dto.TagDto;
import com.webapp.dao.PostsTagsDao;
import com.webapp.entity.Post;
import com.webapp.entity.PostsTags;
import com.webapp.entity.Tag;
import com.webapp.service.Interfaces.PostsTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by sednor-5 on 3/28/17.
 */
@Service
public class PostsTagsServiceImpl implements PostsTagsService {

    @Autowired
    PostsTagsDao postsTagsDao;





    @Override
    public List<PostDto> getPosts(Long id) {

        List<Post> posts = postsTagsDao.getPosts(id);
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            convert(post, postDto);

            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<TagDto> getTags(Long id) {
        List<Tag> tags = postsTagsDao.getTags(id);
        return tags.stream().map(tag -> {
            TagDto tagDto = new TagDto();
            convert(tag, tagDto);
            return tagDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void save(PostsTags postsTags) {

        postsTagsDao.save(postsTags);


    }

    @Override
    public void delete(Long id) {
        postsTagsDao.delete(id);
    }

    @Override
    public void deleteWithPostId(Long postId) {
        postsTagsDao.deleteWithPostId(postId);
    }

    @Override
    public void deleteWithTagId(Long tagId) {
        postsTagsDao.deleteWithTagId(tagId);
    }


    //    private void convert(PostsTags postsTags, PostsTagsDto postsTagsDto) {
//        postsTagsDto.setId(postsTags.getId());
//        postsTagsDto.setPostId(postsTags.getPostId());
//        postsTagsDto.setTagsId(postsTags.getTagsId());
//    }
//
//    private void convert(PostsTagsDto postsTagsDto, PostsTags postsTags) {
//        postsTags.setPostId(postsTagsDto.getPostId());
//        postsTags.setTagsId(postsTagsDto.getTagsId());
//    }
    private void convert(Post post, PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setUserId(post.getUserId());
        postDto.setCategoryId(post.getCategoryId());
    }
    private void convert(Tag tag, TagDto tagDto){
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
    }
}
