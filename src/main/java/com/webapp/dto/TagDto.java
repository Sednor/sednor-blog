package com.webapp.dto;

import java.util.List;

/**
 * Created by sednor-5 on 3/27/17.
 */
public class TagDto {
    private Long id;
    private String name;
    private List<PostDto> posts;

    public TagDto() {
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
