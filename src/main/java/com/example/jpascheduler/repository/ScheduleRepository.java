package com.example.jpascheduler.repository;

import com.example.jpascheduler.dto.SchedulePageResponseDto;
import com.example.jpascheduler.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface ScheduleRepository extends org.springframework.data.jpa.repository.JpaRepository<Schedule, Long> {

    @Query
            ("SELECT new com.example.jpascheduler.dto.SchedulePageResponseDto(" +
            "s.id, s.username, s.title, s.content, COUNT(c), s.createdAt, s.updatedAt) " +
            "FROM Schedule s LEFT JOIN Comment c ON s.id = c.schedule.id " +
            "GROUP BY s.id " +
            "ORDER BY s.updatedAt DESC")
    Page<SchedulePageResponseDto> findAllWithCommentCount(Pageable pageable);
}
