package com.example.jpascheduler.dto;

import com.example.jpascheduler.entity.User;
import lombok.Getter;


//일정 단건 조회 시, 담당자로 배정된 유저 정보 (이메일, 이름) 포함
@Getter
public class AssignedUserDto {

    private Long id;
    private String username;
    private String email;

    // User 엔티티에서 값 추출
    public AssignedUserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }
}
