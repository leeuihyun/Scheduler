package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.domain.model.UserValidCredentials;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleDeleteDto implements UserValidCredentials {

    private Long userId;
    private Long scheduleId;
    private String userPassword;
}
