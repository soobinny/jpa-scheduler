package com.example.jpascheduler.entity;

import com.example.jpascheduler.entity.UserSchedule;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // 작성한 일정들 (1:N)
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Schedule> createdSchedules = new ArrayList<>();

    // 담당 일정들 (N:M 관계를 위한 중간 테이블)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserSchedule> assignedSchedules = new ArrayList<>();

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
        this.updatedAt = LocalDateTime.now();
    }
}
