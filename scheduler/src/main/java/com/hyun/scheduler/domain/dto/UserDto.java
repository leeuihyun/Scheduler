package com.hyun.scheduler.domain.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String userEmail;
    private String userName;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
