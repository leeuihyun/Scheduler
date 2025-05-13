package com.hyun.scheduler.exception;

import com.hyun.scheduler.enums.RequestBodyErrorEnum;
import com.hyun.scheduler.enums.model.ErrorModel;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ScheduleException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String fieldName;
    private final String errorMessage;

    public <T extends ErrorModel> ScheduleException(T errorEnum) {
        super(errorEnum.getErrorMessage());
        this.httpStatus = errorEnum.getHttpStatus();
        this.fieldName = errorEnum.getFieldName();
        this.errorMessage = errorEnum.getErrorMessage();
    }

}
