package com.hyun.scheduler.enums.model;

import org.springframework.http.HttpStatus;

public interface ErrorModel {

    String getFieldName();
    String getErrorMessage();
    HttpStatus getHttpStatus();
}
