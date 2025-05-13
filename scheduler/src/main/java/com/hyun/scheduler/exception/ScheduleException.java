package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.enums.ErrorEnum;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ScheduleException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String fieldName;
    private final String errorMessage;

    public ScheduleException(ErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.httpStatus = errorEnum.getHttpStatus();
        this.fieldName = errorEnum.getFieldName();
        this.errorMessage = errorEnum.getErrorMessage();
    }

}
