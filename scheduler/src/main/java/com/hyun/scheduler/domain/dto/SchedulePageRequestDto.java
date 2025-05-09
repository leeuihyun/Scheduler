package com.hyun.scheduler.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SchedulePageRequestDto {
  private Integer page;
  private Integer size;
}
