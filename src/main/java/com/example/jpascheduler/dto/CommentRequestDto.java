package com.example.jpascheduler.dto;

public class CommentRequestDto {

    private String username;
    private String content;
    private Long scheduleId; // 댓글이 달릴 일정의 ID

    //생성자
    public CommentRequestDto(String username, String content, Long scheduleId) {
        this.username = username;
        this.content = content;
        this.scheduleId = scheduleId;
    }

    //Setter, Getter

    public void setUsername(String username) {
        this.username = username;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getUsername() {
        return username;
    }
    public String getContent() {
        return content;
    }
    public Long getScheduleId() {
        return scheduleId;
    }



}
