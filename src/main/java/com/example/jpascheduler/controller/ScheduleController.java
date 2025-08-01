package com.example.jpascheduler.controller;

import com.example.jpascheduler.dto.ScheduleRequestDto;
import com.example.jpascheduler.dto.ScheduleResponseDto;
import com.example.jpascheduler.entity.Schedule;
import com.example.jpascheduler.repository.ScheduleRepository;
import com.example.jpascheduler.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules") // url : /schedules
public class ScheduleController {

    // Service 연결
    private final ScheduleService scheduleService;

    // 생성자
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 생성 메서드
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto responseDto) {
        return scheduleService.createSchedule(responseDto);
    }

    // 전체 일정 조회 메서드 (여러 개의 일정 -> List로 반환)
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedules() {
        return scheduleService.getAllSchedules();
    }

    // 단건 일정 조회 메서드
    @GetMapping
    public ScheduleResponseDto getSchedule(@PathVariable Long id) {
        return scheduleService.getSchedule(id);
    }

    // 일정 수정 메서드
    @PutMapping
    public ScheduleResponseDto updateSchedule(@PathVariable Long id, @RequestBody ScheduleResponseDto responseDto) {
        return scheduleService.updateSchedule(id, responseDto);
    }


}
