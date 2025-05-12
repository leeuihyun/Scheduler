package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.enums.ErrorEnum;
import org.springframework.http.HttpStatus;

public class MyException extends RuntimeException {

    private final HttpStatus httpStatus;

    public MyException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.httpStatus = errorEnum.getHttpStatus();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
