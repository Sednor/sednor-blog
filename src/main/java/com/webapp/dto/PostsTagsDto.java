package com.webapp.dto;

/**
 * Created by sednor-5 on 3/28/17.
 */
public class PostsTagsDto {

    private Long id;
    private Long postId;
    private Long tagsId;

    public PostsTagsDto() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTagsId() {
        return tagsId;
    }

    public void setTagsId(Long tagsId) {
        this.tagsId = tagsId;
    }

}
