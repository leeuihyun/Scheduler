package com.hyun.scheduler.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
  private Long schedule_id;
  private String schedule_title;
  private String schedule_content;
  private String user_name;
  private LocalDateTime created_at;
  private LocalDateTime updated_at;
}
