# 📅 Scheduler API 문서

---

## 📌 ERD

![Lv1 ERD](scheduler/src/main/resources/static/img/Lv1ERD.png)

---

## 📘 API 명세서 요약

| No                | Method | Description | URL                        | Request      | Response |
|-------------------|--------|-------------|----------------------------|--------------|----------|
| [1](#1-일정-생성)     | POST   | 일정 생성       | /api/schedule              | RequestBody          | 200 OK   |
| [2](#2-전체-일정-조회)  | GET    | 전체 일정 조회    | /api/schedules             | RequestParam | 200 OK   |
| [3](#3-선택-일정-조회)  | GET    | 선택 일정 조회    | /api/schedule/{scheduleId} | PathVariable | 200 OK   |
| [4](#4-선택-일정-수정)  | PUT    | 선택 일정 수정    | /api/schedule/update       | RequestBody | 200 OK   |
| [5](#5-선택-일정-삭제)  | POST   | 선택 일정 삭제    | /api/schedule/delete       | RequestBody | 200 OK   |
| [6](#6-페이지네이션 조회) | GET    | 페이지네이션 조회   | /api/page/schedules              | RequestBody | 200 OK   |

---

## 🔽 상세 API 설명

### 1. 일정 생성
- **URL** : `/api/schedule`
- **Method** : `POST`
### 1-1. Request
- **Request Body**
```json
{
  "scheduleTitle": "schedule title",
  "scheduleContent": "schedule content",
  "userName": "lee",
  "userEmail": "meta212121@naver.com",
  "userPassword": "1234"
}
```
---
### 1-2. Response
- **Request Body**
```json
{
  "userId" : 1,
  "scheduleId" : 1
}
```
---
### 2. 전체 일정 조회
- **URL** : `/api/schedules`
- **Method** : `GET`
### 2-1. Request
- **Request Param**
    - **URL 예시** : `/api/schedules?userId=1&date=2025-05-08`
    - **필수 여부** : `userId` - `required=true`, `date` - `required=false`
---
### 2-2. Response
- **Response Body**
- **desc**
    - 조건에 맞는 조회(전체)
```json
[
  {
    "scheduleId": 1,
    "scheduleTitle": "title1",
    "scheduleContent": "content1",
    "userName": "lee",
    "created_at": "2025-05-08T14:00:00",
    "updated_at": "2025-05-08T14:00:00"
  },
  {
    "scheduleId": 2,
    "scheduleTitle": "title2",
    "scheduleContent": "content2",
    "userName": "lee",
    "created_at": "2025-05-08T14:00:00",
    "updated_at": "2025-05-08T14:00:00"
  }
]
```
---
### 3. 선택 일정 조회
- **URL** : `/api/schedule/{scheduleId}`
- **Method** : `GET`
### 3-1. Request
- **PathVariable**
    - **URL 예시** : `/api/schedule/2`
---
### 3-2. Response
- **Response Body**
- **desc**
    - 식별자에 맞는 조회
```json

  {
    "scheduleId": 1,
    "scheduleTitle": "title",
    "scheduleContent": "content",
    "userName": "lee",
    "created_at": "2025-05-08T14:00:00",
    "updated_at": "2025-05-08T14:00:00"
  }

```
---
### 4. 선택 일정 수정
- **URL** : `/api/schedule/update`
- **Method** : `PUT`
### 4-1. Request
- **RequestBody**
```json
{
  "scheduleId": 1,
  "userPassword": "12345",
  "scheduleTitle" : "수정한 제목",
  "scheduleContent" : "수정한 내용",
  "userName" : "수정한 작성자명",
  "userId" : 1
}
```
### 4-2. Response
- **Response Body**
```json
  {
    "scheduleId": 1,
    "scheduleTitle": "title",
    "scheduleContent": "content",
    "userName": "lee",
    "created_at": "2025-05-08T14:00:00",
    "updated_at": "2025-05-08T14:00:00"
  }

```
---
### 5. 선택 일정 삭제
- **URL** : `/api/schedule/delete`
- **Method** : `POST`
### 5-1. Request
- **RequestBody**
```json
{
  "scheduleId": 1,
  "userPassword": "12345"
}
```
### 6. 페이지네이션 조회
- **URL** : `/api/page/schedules`
- **Method** : `GET`
### 6-1. Request
- **Request Param**
  - **URL 예시** : `/api/page/schedules?page=0&size=10`
  - **필수 여부** : `page` - `required=false`, `size` - `required=false`
---
### 6-2. Response
- **Response Body**
- **desc**
  - 페이지네이션 조회
  - page default value - 0, size default value - 10
  - 수정일 기준 내림차순 정렬
```json
[
  {
    "scheduleId": 19,
    "scheduleTitle": "Test 21 11Title",
    "scheduleContent": "Test 2 Content",
    "userName": "이의현",
    "created_at": "2025-05-09T17:10:41",
    "updated_at": "2025-05-09T17:10:41"
  },
  {
    "scheduleId": 20,
    "scheduleTitle": "title2",
    "scheduleContent": "content2",
    "userName": "lee",
    "created_at": "2025-05-08T14:00:00",
    "updated_at": "2025-05-08T14:00:00"
  }
]
```
---