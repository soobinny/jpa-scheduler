package com.example.jpascheduler.repository;

import com.example.jpascheduler.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
}
