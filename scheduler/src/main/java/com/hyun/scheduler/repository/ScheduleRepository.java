package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
  Long saveSchedule(String scheduleTitle, String scheduleContent, Long userId);

  Optional<ScheduleResponseDto> findScheduleById(Long scheduleId);

  List<ScheduleResponseDto> findAllSchedules(Long userId, Optional<LocalDate> optionalDate);

  Integer updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

  Integer deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

  List<ScheduleResponseDto> findPageSchedules(Integer page, Integer size);
}
