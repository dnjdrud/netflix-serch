
# 🎬 Java Netflix Content Recommendation System

본 프로젝트는 사용자의 선호도를 기반으로 넷플릭스 콘텐츠를 검색하고 추천하는 Java 기반의 콘솔 애플리케이션입니다.

## 🔍 주요 기능

- 사용자 로그인 및 인증
- 사용자 선호 장르/배우/감독 정보 저장
- 조건 기반 넷플릭스 컨텐츠 검색 (배우, 감독, 장르 등)
- 검색 결과 출력 및 사용자 선호도 반영 저장

## 🧩 주요 클래스 설명

| 클래스명                           | 설명                                                                                                       |
|------------------------|-----------------------------------------------------------------|
| `UserDAO`                     | 사용자 정보 조회 (`findByUsername`)                                                 |
| `UserPreferenceDAO`  | 사용자 선호도 관련 데이터베이스 접근 메서드들 (`insertPreference` 등) |
| `ShowDAO`                   | 다양한 조건(배우, 감독, 장르 등)으로 공연/영화 검색 기능 제공                    |
| `User`                            | 사용자 객체 모델 (getter/setter 포함)                                                      |
| `DBConfig`                    | DB 설정 클래스 (구체 로직 없음)                                                              |
| `SearchUI`                     | 콘솔 UI 메뉴 처리 (`showMenuAndSelect`, `handleChoice`)            |
| `AuthService`                | 로그인 처리 서비스 (`login`)                                                                   |
| `SearchService`            | 다양한 조건 기반의 검색 서비스                                                                |
| `PreferenceService`     | 사용자 선호도 출력 및 저장                                                                       |
| `Main`                            | 앱 진입점 (main 메서드 포함으로 추정됨)                                                 | 

## ✅ 실행 방법

1. Java 8 이상 설치
2. IDE(Eclipse, IntelliJ 등)에서 프로젝트 열기
3. `Main.java` 실행

## 📦 사용 기술

- Java (JDK 8+)
- JDBC
- 콘솔 기반 UI

## 🧑‍💻 작성자

- 이름: 우원경
- 날짜: 2025-04-03

---
