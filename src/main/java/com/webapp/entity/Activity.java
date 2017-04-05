package com.webapp.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by sednor-5 on 3/23/17.
 */
public class Activity {

    private Long id;
    private String status;
    private LocalDateTime date;
    private Long userId;

    public Activity() {
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
        return "Activity{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
