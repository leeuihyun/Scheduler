package com.hyun.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateRequestDto implements UserValidCredentials {
  private Long scheduleId;
  private String scheduleTitle;
  private String scheduleContent;
  private Long userId;
  private String userName;
  private String userPassword;
}
