package com.hyun.scheduler.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleRequestDto {
  private String schedule_title;
  private String schedule_content;
  private String user_name;
  private String password;
}
