package com.hyun.scheduler.domain.dto;

import com.hyun.scheduler.domain.model.UserValidNameAndPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto implements UserValidNameAndPassword {

    @NotBlank
    @Size(max = 50)
    private String scheduleTitle;

    @NotBlank
    @Size(max = 200)
    private String scheduleContent;

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    private String userName;

    @NotBlank
    private String userPassword;
}
