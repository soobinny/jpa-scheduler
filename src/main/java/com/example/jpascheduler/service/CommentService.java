package com.example.jpascheduler.service;

import com.example.jpascheduler.dto.CommentRequestDto;
import com.example.jpascheduler.dto.CommentResponseDto;
import com.example.jpascheduler.entity.Comment;
import com.example.jpascheduler.entity.Schedule;
import com.example.jpascheduler.repository.CommentRepository;
import com.example.jpascheduler.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private ScheduleRepository scheduleRepository;

    // 생성자
    public CommentService(CommentRepository commentRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // 댓글 저장 메서드
    public CommentResponseDto createComment(CommentRequestDto requestDto) {
        Schedule schedule = scheduleRepository.findById(requestDto.getScheduleId())
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 존재하지 않습니다"));

        Comment comment = new Comment(
                requestDto.getContent(),
                requestDto.getUsername(),
                schedule
        );

        Comment saved = commentRepository.save(comment);
        return new CommentResponseDto(saved);
    }

    // 댓글 전체 조회 메서드
    public List<CommentResponseDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }

    //댓글 단건 조회 메서드
    public CommentResponseDto getComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        return new CommentResponseDto(comment);
    }

    //댓글 수정 메서드
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        comment.update(requestDto.getContent());
        return new CommentResponseDto(comment);
    }

    //댓글 삭제 메서드
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));

        commentRepository.delete(comment);
    }
}
