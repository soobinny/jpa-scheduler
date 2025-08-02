package com.example.jpascheduler.repository;

import com.example.jpascheduler.entity.UserSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserScheduleRepository extends JpaRepository<UserSchedule, Long> {
}