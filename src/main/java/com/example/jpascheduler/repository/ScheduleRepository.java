package com.example.jpascheduler.repository;

import com.example.jpascheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository  extends JpaRepository<Schedule,Long> {
}
