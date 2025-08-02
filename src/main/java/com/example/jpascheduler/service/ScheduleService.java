package com.example.jpascheduler.service;

import com.example.jpascheduler.dto.SchedulePageResponseDto;
import com.example.jpascheduler.dto.ScheduleRequestDto;
import com.example.jpascheduler.dto.ScheduleResponseDto;
import com.example.jpascheduler.entity.Schedule;
import com.example.jpascheduler.entity.User;
import com.example.jpascheduler.entity.UserSchedule;
import com.example.jpascheduler.repository.ScheduleRepository;
import com.example.jpascheduler.repository.UserRepository;
import com.example.jpascheduler.repository.UserScheduleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    // Repository 의존성 주입 연결
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final UserScheduleRepository userScheduleRepository;

    //생성자
    public ScheduleService(ScheduleRepository scheduleRepository,
                           UserRepository userRepository,
                           UserScheduleRepository userScheduleRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.userScheduleRepository = userScheduleRepository;
    }

    //일정 생성 메서드
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + dto.getUserId()));

        Schedule schedule = new Schedule(dto.getTitle(), dto.getContent(), user);
        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule);
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

    // 페이징 조회 메서드
    public Page<SchedulePageResponseDto> getPagedSchedules(Pageable pageable) {
        return scheduleRepository.findAllWithCommentCount(pageable);
    }

    // 삭제 : 영속성 전이
    @Transactional
    public void deleteSchedule(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + id));

        scheduleRepository.delete(schedule);  // 연관 함께 삭제됨
    }

    //담당자 배정 메서드
    @Transactional
    public void assignUserToSchedule(Long scheduleId, Long userId) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다. id=" + scheduleId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + userId));

        UserSchedule userSchedule = new UserSchedule(user, schedule);
        userScheduleRepository.save(userSchedule);
    }



}
