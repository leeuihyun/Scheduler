package com.hyun.scheduler.service;

import com.hyun.scheduler.domain.dto.ScheduleCreateResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleCreateRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    ScheduleCreateResponseDto saveSchedule(ScheduleCreateRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedules(Long user_id, Optional<LocalDate> optionalDate);

    ScheduleResponseDto findScheduleById(Long schedule_id);

    ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

    void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto);

    List<ScheduleResponseDto> findPageSchedules(Integer page, Integer size);
}
