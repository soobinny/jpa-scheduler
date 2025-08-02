package com.example.jpascheduler.controller;

import com.example.jpascheduler.dto.SchedulePageResponseDto;
import com.example.jpascheduler.dto.ScheduleRequestDto;
import com.example.jpascheduler.dto.ScheduleResponseDto;
import com.example.jpascheduler.entity.Schedule;
import com.example.jpascheduler.repository.ScheduleRepository;
import com.example.jpascheduler.service.ScheduleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
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

    //페이징
    @GetMapping("/paged")
    public Page<SchedulePageResponseDto> getPagedSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) { //디폴트 페이지 크기 10

        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        return scheduleService.getPagedSchedules(pageable);
    }

    //일정 + 댓글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.ok("일정이 삭제되었습니다."); // 200 ok
    }

    //담당자 api 등록
    @PostMapping("/{scheduleId}/assign/{userId}")
    public ResponseEntity<String> assignUserToSchedule(@PathVariable Long scheduleId, @PathVariable Long userId) {
        scheduleService.assignUserToSchedule(scheduleId, userId);
        return ResponseEntity.ok("담당자가 등록되었습니다.");
    }

}
