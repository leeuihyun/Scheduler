package com.hyun.scheduler.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {

    private Long scheduleId;
    private String scheduleTitle;
    private String scheduleContent;
    private String userName;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
