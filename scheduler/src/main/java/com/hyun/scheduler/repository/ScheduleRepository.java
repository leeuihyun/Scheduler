package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
  Long saveSchedule(ScheduleRequestDto scheduleRequestDto);

  Optional<ScheduleResponseDto> findScheduleById(Long schedule_id);

  List<ScheduleResponseDto> findAllSchedules(String user_name, Optional<LocalDate> optionalDate);

  Integer updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

  Integer deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);
}
