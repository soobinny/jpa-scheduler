package com.example.jpascheduler.service;

import com.example.jpascheduler.dto.UserRequestDto;
import com.example.jpascheduler.dto.UserResponseDto;
import com.example.jpascheduler.entity.User;
import com.example.jpascheduler.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 생성
    @Transactional
    public UserResponseDto createUser(UserRequestDto dto) {
        User user = new User(dto.getUsername(), dto.getEmail());
        return new UserResponseDto(userRepository.save(user));
    }

    // 유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + id));
        return new UserResponseDto(user);
    }

    // 유저 전체 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }

    // 유저 삭제
    @Transactional
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다. id=" + id));
        userRepository.delete(user);
    }
}
