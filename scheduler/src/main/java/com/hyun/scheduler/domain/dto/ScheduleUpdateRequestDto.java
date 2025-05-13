package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.domain.model.UserValidCredentials;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto implements UserValidCredentials {

    @NotNull
    @Positive
    private Long scheduleId;

    @NotBlank
    private String scheduleTitle;

    @NotBlank
    private String scheduleContent;

    @NotNull
    @Positive
    private Long userId;

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;
}
