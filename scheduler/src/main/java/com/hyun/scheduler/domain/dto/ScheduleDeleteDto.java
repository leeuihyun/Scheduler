package com.hyun.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleDeleteDto {
  private Long schedule_id;
  private String password;
}
