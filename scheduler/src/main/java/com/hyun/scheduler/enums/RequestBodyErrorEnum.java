package com.hyun.scheduler.enums;

import org.springframework.http.HttpStatus;

public enum ErrorEnum implements ErrorModel{
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

    ErrorEnum(HttpStatus httpStatus, String fieldName, String errorMessage) {
        this.httpStatus = httpStatus;
        this.fieldName = fieldName;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getFieldName() {
        return this.fieldName;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    public static ErrorEnum findEnumByFieldName(String fieldName) {
        for(ErrorEnum error : ErrorEnum.values()) {
            if(error.getFieldName().equals(fieldName)){
                return error;
            }
        }

        return ErrorEnum.UNKNOWN_ERROR;
    }
}
