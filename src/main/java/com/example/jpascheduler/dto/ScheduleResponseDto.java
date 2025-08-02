package com.example.jpascheduler.dto;

import com.example.jpascheduler.entity.Schedule;
import com.example.jpascheduler.entity.UserSchedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleResponseDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 담당자 리스트
    private List<AssignedUserDto> assignedUsers;

    // 생성자
    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();

        // 담당자 정보 리스트로 변환
        this.assignedUsers = schedule.getAssignedUsers().stream()
                .map(us -> new AssignedUserDto(us.getUser()))
                .collect(Collectors.toList());
    }

    // Getter
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<AssignedUserDto> getAssignedUsers() {
        return assignedUsers;
    }
}
