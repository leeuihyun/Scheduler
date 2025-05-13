package com.hyun.scheduler.enums;

import com.hyun.scheduler.enums.model.ErrorModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RequestBodyErrorEnum implements ErrorModel {

    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "userPassword", "유저 비밀번호가 일치하지 않습니다."),
    USER_MODIFY_FAIL(HttpStatus.NOT_FOUND, "userId", "유저 업데이트 요청이 실패했습니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "scheduleId","존재하지 않는 일정입니다."),
    TITLE_LONG(HttpStatus.NOT_FOUND, "scheduleTitle","제목은 50자까지 작성 가능합니다."),
    CONTENT_LONG(HttpStatus.NOT_FOUND, "scheduleContent","내용은 200자까지 작성 가능합니다."),
    EMAIL_INVALID(HttpStatus.NOT_FOUND,"scheduleEmail", "이메일 형식이 올바르지 않습니다."),
    UNKNOWN_ERROR(HttpStatus.BAD_REQUEST, "unknown", "알 수 없는 필드 오류입니다.");

    private final HttpStatus httpStatus;
    private final String fieldName;
    private final String errorMessage;
}
