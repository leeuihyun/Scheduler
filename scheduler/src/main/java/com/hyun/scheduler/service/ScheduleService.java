package com.hyun.scheduler.service;

import com.hyun.scheduler.domain.dto.ScheduleRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
  Long saveSchedule(ScheduleRequestDto scheduleRequestDto);

  List<ScheduleResponseDto> findAllSchedules(String user_name, Optional<LocalDate> optionalDate);

  ScheduleResponseDto findScheduleById(Integer schedule_id);
}
