package com.example.jpascheduler.dto;

import com.example.jpascheduler.entity.Schedule;

import java.time.LocalDateTime;

public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.username = schedule.getUsername();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

    //Getter
     public Long getId() {
        return id;
     }

     public String getTitle() {
        return title;
     }

     public String getContent() {
        return content;
     }

     public String getUsername() {
        return username;
     }

     public LocalDateTime getCreatedAt() {
        return createdAt;
     }

     public LocalDateTime getUpdatedAt() {
        return updatedAt;
     }


}
