package com.hyun.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto {
  private Long schedule_id;
  private String schedule_title;
  private String schedule_content;
  private String user_name;
  private String password;
}
