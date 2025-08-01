package com.example.jpascheduler.dto;

public class ScheduleRequestDto {

    private String username;
    private String title;
    private String content;

    //기본 생성자
    public ScheduleRequestDto() {}

    //생성자
    public ScheduleRequestDto(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    //Setter, Getter 구현

    public void setUsername(String username) {
        this.username = username;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }

}
