package com.hyun.scheduler.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorEnum {
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "유저정보가 일치하지 않습니다."),
    SCHEDULE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 일정입니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;

    ErrorEnum(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}
