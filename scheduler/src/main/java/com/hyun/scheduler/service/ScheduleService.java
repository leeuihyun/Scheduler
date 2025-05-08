package com.hyun.scheduler.service;

import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {
  Long saveSchedule(ScheduleRequestDto scheduleRequestDto);

  List<ScheduleResponseDto> findAllSchedules(String user_name, Optional<LocalDate> optionalDate);

  ScheduleResponseDto findScheduleById(Long schedule_id);

  ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

  void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);
}
