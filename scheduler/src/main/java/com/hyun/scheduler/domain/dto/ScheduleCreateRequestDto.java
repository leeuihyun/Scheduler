package com.hyun.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateRequestDto implements UserValidNameAndPassword {
  private String scheduleTitle;
  private String scheduleContent;
  private String userEmail;
  private String userName;
  private String userPassword;
}
