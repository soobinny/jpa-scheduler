package com.example.jpascheduler.service;

import com.example.jpascheduler.dto.ScheduleRequestDto;
import com.example.jpascheduler.dto.ScheduleResponseDto;
import com.example.jpascheduler.entity.Schedule;
import com.example.jpascheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    // Repository 의존성 주입 연결
    private final ScheduleRepository scheduleRepository;

    //생성자
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //일정 생성 메서드
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto ) {
        Schedule schedule = new Schedule(
                requestDto.getUsername(),
                requestDto.getTitle(),
                requestDto.getContent()
        );

        Schedule saved = scheduleRepository.save(schedule);
        return new ScheduleResponseDto(saved);
    }

    // 전체 일정 조회 메서드
    public List<ScheduleResponseDto> getAllSchedules(){
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();
    }

    // 단건 일정 조회 메서드 (값 없을 경우 예외처리)
    public ScheduleResponseDto getSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        return new ScheduleResponseDto(schedule);
    }

    // 일정 수정 메서드
    public ScheduleResponseDto updateSchedule(Long id, ScheduleResponseDto requestDto) {
        // 1. 기존 일정 조회
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다."));

        // 2. 수정
        schedule.update(requestDto.getTitle(), requestDto.getContent());

        return new ScheduleResponseDto(schedule);
    }
}
