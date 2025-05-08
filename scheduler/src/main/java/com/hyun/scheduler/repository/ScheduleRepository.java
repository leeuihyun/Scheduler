package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
  Long saveSchedule(ScheduleRequestDto scheduleRequestDto);

  Optional<ScheduleResponseDto> findScheduleById(Integer schedule_id);

  List<ScheduleResponseDto> findAllSchedules(String user_name, Optional<LocalDate> optionalDate);
}
