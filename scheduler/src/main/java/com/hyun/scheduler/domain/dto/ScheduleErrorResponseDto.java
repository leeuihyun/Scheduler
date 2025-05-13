package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.exception.ScheduleException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ScheduleErrorResponseDto {
    private final String errorMessage;
    private final String fieldName;
    private final HttpStatus httpStatus;

    public ScheduleErrorResponseDto(ScheduleException ex) {
        this.errorMessage = ex.getErrorMessage();
        this.fieldName = ex.getFieldName();
        this.httpStatus = ex.getHttpStatus();
    }
}
