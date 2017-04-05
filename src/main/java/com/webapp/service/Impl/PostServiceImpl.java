package com.webapp.service.Impl;

import com.webapp.dto.ActivityDto;
import com.webapp.dto.PostDto;
import com.webapp.dto.TagDto;
import com.webapp.dao.PostDao;
import com.webapp.dao.UserDao;
import com.webapp.entity.Post;
import com.webapp.entity.PostsTags;
import com.webapp.entity.User;
import com.webapp.service.Interfaces.PostService;
import com.webapp.service.Interfaces.TagService;
import com.webapp.service.Interfaces.ActivityService;
import com.webapp.service.Interfaces.PostsTagsService;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by sednor-5 on 3/27/17.
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDao postDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TagService tagsService;

    @Autowired
    private PostsTagsService postsTagsService;

    @Autowired
    private ActivityService activityService;

    @Override
    public PostDto save(PostDto postDto) {
        User user = userDao.findOne(postDto.getUserId());
        if (user.isStatus()) {
            Post post = new Post();
            convert(postDto, post);
            post = postDao.save(post);
            postDto.setId(post.getId());

            String status = String.format("User id=%s create post with id=%s",
                    postDto.getUserId(), postDto.getId());
            saveActivity(post, status);


            Long id = post.getId();
            savePostTags(postDto, id);
            return postDto;
        }
        return null;
    }


    @Override
    public List<PostDto> findAll() {
        List<Post> posts = postDao.findAll();
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            convert(post, postDto);
            //add list tags
            postDto.setTagsList(postsTagsService.getTags(postDto.getId()));
            return postDto;
        }).collect(Collectors.toList());

    }

    @Override
    public PostDto findOne(Long id) {
        PostDto postDto = new PostDto();
        Post post = postDao.findOne(id);
        convert(post, postDto);
        //
        postDto.setTagsList(postsTagsService.getTags(postDto.getId()));
        return postDto;
    }

    @Override
    public void delete(Long id) {

        Post post = postDao.findOne(id);
        if (post == null) {
            return;
        }

        postsTagsService.deleteWithPostId(id);
        postDao.delete(id);


        String status = String.format("user with id  %s was delete post id=%s",
                post.getUserId(), post.getId());
        saveActivity(post, status);

    }

    @Override
    public List<PostDto> findUserPosts(Long idUser) {
        List<Post> posts = postDao.findUserPosts(idUser);
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            convert(post, postDto);
            //
            postDto.setTagsList(postsTagsService.getTags(postDto.getId()));
            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> findCategoryPosts(Long idCategory) {
        List<Post> posts = postDao.findCategoryPosts(idCategory);
        return posts.stream().map(post -> {
            PostDto postDto = new PostDto();
            convert(post, postDto);
            //
            postDto.setTagsList(postsTagsService.getTags(postDto.getId()));
            return postDto;
        }).collect(Collectors.toList());
    }

    @Override
    public void update(PostDto postDto) {

        Post post = postDao.findOne(postDto.getId());
        if (post == null) {
            return;
        }
        convert(postDto, post);
        postDao.update(post);
        String status = String.format("User id=%s update post with id=%s",
                post.getUserId(), post.getId());
        saveActivity(post, status);

    }

    private void convert(PostDto postDto, Post post) {
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setUserId(postDto.getUserId());
        post.setCategoryId(postDto.getCategoryId());
    }

    private void convert(Post post, PostDto postDto) {
        postDto.setId(post.getId());
        postDto.setDescription(post.getDescription());
        postDto.setTitle(post.getTitle());
        postDto.setUserId(post.getUserId());
        postDto.setCategoryId(post.getCategoryId());
    }

    private void saveActivity(Post post, String status) {
        LocalDateTime date = LocalDateTime.now();
        ActivityDto activityDto = new ActivityDto();
        activityDto.setDate(date);
        activityDto.setUserId(post.getUserId());
        activityDto.setStatus(status);
        activityService.save(activityDto);
    }

    private void convertPostTags(PostsTags postsTags, Long postId, Long tagId) {
        postsTags.setPostId(postId);
        postsTags.setTagsId(tagId);
    }
    private void savePostTags(PostDto postDto, Long id) {

        List<TagDto> tags = tagsService.findAll();
        postDto.getTagsList().forEach(item -> {
            PostsTags postsTags = new PostsTags();
            if (item.getId() != null) {
                convertPostTags(postsTags, id, item.getId());

                //get tags if tagDto have id ,
            } else {

//                TagDto tagDto = new TagDto();
//                tagDto.setName(item.getName());

                TagDto tagDto = IterableUtils.find(tags, tag -> Objects.equals(item.getName(), tag.getName()));
                if (tagDto == null) {
                    tagDto = new TagDto();
                    tagDto.setName(item.getName());
                }
//                Long tagIdTest = Optional.of(tagDto.getId()).orElse(tagsService.save(tagDto).getId());
                //               System.out.println(tagIdTest);
                convertPostTags(postsTags, id, tagDto.getId() != null ? tagDto.getId() : tagsService.save(tagDto).getId());
                // save tags if not exist
            }
            // save Post tags
            postsTagsService.save(postsTags);
        });
    }
}
