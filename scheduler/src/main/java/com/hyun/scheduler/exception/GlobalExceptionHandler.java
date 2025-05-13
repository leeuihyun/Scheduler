package com.hyun.scheduler.exception;

import com.hyun.scheduler.domain.dto.ScheduleErrorResponseDto;
import com.hyun.scheduler.enums.ParamErrorEnum;
import com.hyun.scheduler.enums.RequestBodyErrorEnum;
import com.hyun.scheduler.enums.ValidErrorEnum;
import com.hyun.scheduler.enums.utils.ErrorUtils;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path.Node;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ScheduleException.class)
    public ResponseEntity<ScheduleErrorResponseDto> handleRequestBodyException(ScheduleException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new ScheduleErrorResponseDto(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ScheduleErrorResponseDto>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldError> errors = ex.getBindingResult().getFieldErrors();
        List<ScheduleErrorResponseDto> errorList = new ArrayList<>();

        for(FieldError error : errors) {
            errorList.add
                (new ScheduleErrorResponseDto
                    (new ScheduleException
                        (ErrorUtils.findEnumByFieldName(ValidErrorEnum.class, error.getField(), ValidErrorEnum.UNKNOWN_ERROR))));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ScheduleErrorResponseDto> handleParamException(ConstraintViolationException ex) {
        ConstraintViolation<?> violation = ex.getConstraintViolations().iterator().next();
        String field = null;

        for (Node node : violation.getPropertyPath()) {
            field = node.getName();
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
            (new ScheduleErrorResponseDto
                (new ScheduleException
                    (ErrorUtils.findEnumByFieldName(ParamErrorEnum.class, field, ParamErrorEnum.UNKNOWN_ERROR))));
    }

}