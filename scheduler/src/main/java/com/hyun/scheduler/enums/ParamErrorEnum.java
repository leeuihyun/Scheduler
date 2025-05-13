package com.hyun.scheduler.enums;

import com.hyun.scheduler.enums.model.ErrorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ParamErrorEnum implements ErrorModel {

    USER_ID_SEARCH_FAIL(HttpStatus.NOT_FOUND, "userId", "존재하지 않는 유저 인덱스입니다."),
    DATE_TYPE_MISMATCH(HttpStatus.NOT_FOUND, "date", "날짜 형식이 올바르지 않습니다."),
    SCHEDULE_ID_SEARCH_FAIL(HttpStatus.NOT_FOUND, "scheduleId", "존재하지 않는 스케줄 인덱스입니다."),
    SCHEDULE_PAGE_OUTBOUND(HttpStatus.NOT_FOUND, "page", "스케줄 페이지가 0보다 작습니다."),
    SCHEDULE_SIZE_OUTBOUND(HttpStatus.NOT_FOUND, "size", "스케줄의 사이즈가 5보다 작습니다."),
    UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "unknown", "알 수 없는 필드 오류입니다.");

    private final HttpStatus httpStatus;
    private final String fieldName;
    private final String errorMessage;
}
