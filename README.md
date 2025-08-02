# jpa-scheduler

- 언어: Java
- 프레임워크: Spring Boot
- 빌드 도구: Gradle
- 데이터베이스: MySQL  
- IDE: IntelliJ IDEA

---

## 프로젝트 개요

- Spring Boot와 JPA를 기반으로 구현한 일정 관리 백엔드 프로젝트 
- 단계별 기능 구현을 통해 CRUD, 연관관계 매핑, 페이징, 중간 테이블 활용, 지연 로딩 등을 구현
  
---

## 주요 기능

### 일정 기능
- 일정 등록, 단건 조회, 전체 조회, 수정, 삭제
- 일정 작성자와의 연관관계 (`@ManyToOne`)
- 일정에 여러 명의 담당자 배정 (N:M 관계, 중간 엔티티로 구현)
- 페이징 처리 및 댓글 수 포함 조회
- 일정 삭제 시 댓글 자동 삭제 (영속성 전이)

### 유저 기능
- 유저 등록, 단건 조회, 전체 조회, 삭제
- 작성자와 담당자로 일정에 연관

### 담당자 배정
- `/schedules/{scheduleId}/assign/{userId}` API를 통해 일정에 유저를 담당자로 배정
- 중간 엔티티 `UserSchedule`을 통한 다대다 관계 처리
- `@ManyToMany`는 사용하지 않고 명시적으로 연관관계 설계

### 조회 최적화
- 단건 조회 시에만 담당자 정보 포함 (지연 로딩 적용)
- 전체 조회 시에는 담당자 정보 로딩 생략하여 성능 최적화

---

## 구현 과정

- JPA의 연관관계 매핑: 1:N, N:M, 단방향 vs 양방향
- 중간 엔티티를 활용한 명시적 N:M 구현
- 연관 엔티티 삭제를 위한 영속성 전이(Cascade)
- 페이징 처리 및 JPQL 활용
- 지연 로딩(LAZY)을 통한 조회 최적화
- DTO 분리 및 계층 구조 설계

---

## 클래스 구조

```
jpa-scheduler/
├── controller/
│   ├── ScheduleController.java
│   └── UserController.java
│
├── dto/
│   ├── AssignedUserDto.java
│   ├── SchedulePageResponseDto.java
│   ├── ScheduleRequestDto.java
│   ├── ScheduleResponseDto.java
│   ├── UserRequestDto.java
│   └── UserResponseDto.java
│
├── entity/
│   ├── Comment.java
│   ├── Schedule.java
│   ├── User.java
│   └── UserSchedule.java
│
├── repository/
│   ├── CommentRepository.java
│   ├── ScheduleRepository.java
│   ├── UserRepository.java
│   └── UserScheduleRepository.java
│
├── service/
│   ├── ScheduleService.java
│   └── UserService.java
│
└── JpaschedulerApplication.java // gitignore

```
---

## ERD

<img width="578" height="548" alt="Image" src="https://github.com/user-attachments/assets/9388da67-d8f2-4284-8754-135a04e2fc46" />

---

## API

| 기능             | HTTP | URI                                  |
|------------------|------|---------------------------------------|
| 전체 일정 조회     | GET  | /schedules                            |
| 일정 단건 조회     | GET  | /schedules/{id}                       |
| 일정 등록         | POST | /schedules                            |
| 일정 수정         | PUT  | /schedules/{id}                       |
| 일정 삭제         | DELETE | /schedules/{id}                     |
| 유저 등록         | POST | /users                                |
| 유저 단건 조회     | GET  | /users/{id}                           |
| 유저 전체 조회     | GET  | /users                                |
| 유저 삭제         | DELETE | /users/{id}                         |
| 담당자 등록       | POST | /schedules/{scheduleId}/assign/{userId} |

---

## 트러블슈팅
[Velog](https://velog.io/@soobinny/JAVASpring-Scheduler-TroubleShooting-Spring-JPA-Optional-%EC%B2%98%EB%A6%AC-findById.get)
