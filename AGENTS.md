# OpsFlow Development Guide

## Goal
Java/Spring 백엔드 취업용 학습 프로젝트.
코드 생성보다 이해, 테스트, 검증을 우선한다.

## Stack
- Java 21
- Spring Boot 3
- Spring Data JPA
- MySQL
- Redis
- JUnit5
- Gradle

## Architecture
Controller
→ Service
→ Repository
→ Database

## Rules
- Constructor Injection 사용
- Controller에 비즈니스 로직 작성 금지
- Entity를 API 응답으로 직접 노출하지 않음
- Request/Response DTO 사용
- Transaction 범위를 명확하게 유지
- 핵심 기능에는 테스트 작성
- 불필요한 dependency 추가 금지

## AI Rules
- 요구사항을 임의로 변경하지 않는다.
- 실패 테스트를 삭제해서 통과시키지 않는다.
- 구현 전에 기존 구조를 먼저 조사한다.
- 큰 변경은 먼저 계획을 제시한다.
- 이해하기 어려운 코드는 이유를 설명한다.

## Verification
작업 완료 전:
1. ./gradlew test
2. ./gradlew build
3. 변경 파일 확인
4. git diff 확인