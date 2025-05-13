package com.hyun.scheduler.enums.utils;

import com.hyun.scheduler.enums.model.ErrorModel;

public class ErrorUtils {
    private ErrorUtils() {};

    public static <T extends Enum<T> & ErrorModel> T findEnumByFieldName(Class<T> enumClass, String fieldName, T defaultEnum) {

        for(T error : enumClass.getEnumConstants()) {
            if(error.getFieldName().equals(fieldName)){
                return error;
            }
        }

        return defaultEnum;
    }
}
