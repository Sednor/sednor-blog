package com.webapp.dto;

import java.util.List;

/**
 * Created by sednor-5 on 3/24/17.
 */
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private Long categoryId;
    private Long userId;
    private List<TagDto> tagsList;

    public PostDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<TagDto> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<TagDto> tagsList) {
        this.tagsList = tagsList;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                '}';
    }

}
