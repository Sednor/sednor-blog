package com.webapp.dto;


import java.time.LocalDateTime;

/**
 * Created by sednor-5 on 3/23/17.
 */
public class ActivityDto {

    private Long id;
    private String status;
    private LocalDateTime date;
    private Long userId;

    public ActivityDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ActivityDto{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
