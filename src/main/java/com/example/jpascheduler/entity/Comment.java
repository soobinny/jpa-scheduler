package com.example.jpascheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String content;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /*
    * 댓글과 일정의 연관관계 (Schedule과 연결)
    * 다(댓글) : 1(일정)
    * fetch : 지연 로딩 설정
    * schedule_id : 외래키
    * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="schedule_id", nullable = false)
    private Schedule schedule;


    //기본 생성자
    public Comment() {}


    //생성자
    public Comment(String username, String content, Schedule schedule) {
        this.username = username;
        this.content = content;
        this.schedule = schedule;
    }

    //댓글 수정 메서드
    public void update(String content) {
        this.content = content;
    }
}
