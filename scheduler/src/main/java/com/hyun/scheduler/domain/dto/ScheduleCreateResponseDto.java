package com.hyun.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateResponseDto {
  private Long userId;
  private Long scheduleId;
}
