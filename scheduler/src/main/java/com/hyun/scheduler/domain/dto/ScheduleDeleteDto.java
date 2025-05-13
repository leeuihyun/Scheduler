package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.domain.model.UserValidCredentials;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleDeleteDto implements UserValidCredentials {

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long scheduleId;

    @NotBlank
    private String userPassword;
}
