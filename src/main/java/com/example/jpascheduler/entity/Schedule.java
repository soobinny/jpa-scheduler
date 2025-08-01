package com.example.jpascheduler.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
@Entity
public class Schedule {

    // PK => id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id 1씩 자동 증가
    private Long id;

    private String username; // 작성자
    private String title; // 제목
    private String content; // 내용

    @CreationTimestamp
    private LocalDateTime createdAt; // 생성 시간
    @UpdateTimestamp
    private LocalDateTime updatedAt; // 수정 시간

    //생성자
    public Schedule(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }

    //기본 생성자 -> JPA는 반드시 기본 생성자가 필요함(public or protect)
    public Schedule() {}


    // 수정 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
