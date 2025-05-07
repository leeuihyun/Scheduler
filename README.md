# Scheduler

---

## ERD
![Lv1 ERD](scheduler/src/main/resources/static/img/Lv1ERD.png)

## API 명세서

|  | Method | Description            | URL                        | Request                                                                                               | Response                                                                                                                                                |
|--|--------|------------------------|----------------------------|-------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1 | `POST`   | 일정 생성       | /api/schedule              | RequestBody: `{ "title": "string", "content": "string" ,"user_name": "string", "password": "string"}` | 200 OK                                                                                                                                                  |
| 2 | `GET`    | 전체 일정 조회  | /api/schedules             | 테스트1                                                                                                  | 200 OK, <br/>ResponseBody : `[{ "title": "string", "content": "string" ,"user_name": "string", "created_at" : "string", "updated_at":"string"}, . . .]` |
| 3 | `GET`    | 선택 일정 조회               | /api/schedule/{scheduleId} | PathVariable : `scheduleId: Long`                                                                     | 200 OK, <br/>ResponseBody : `{ "title": "string", "content": "string" ,"user_name": "string", "created_at" : "string", "updated_at":"string"}`          |
