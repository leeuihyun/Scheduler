package com.hyun.scheduler.service;

import com.hyun.scheduler.domain.dto.ScheduleRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.repository.ScheduleRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScheduleServiceImpl implements ScheduleService{

  @Autowired
  private ScheduleRepository scheduleRepository;

  @Override
  public Long saveSchedule(ScheduleRequestDto scheduleRequestDto) {
    return scheduleRepository.saveSchedule(scheduleRequestDto);
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(String user_name, Optional<LocalDate> optionalDate) {
    return scheduleRepository.findAllSchedules(user_name, optionalDate);
  }

  @Override
  public ScheduleResponseDto findScheduleById(Long schedule_id) {
    Optional<ScheduleResponseDto> scheduleResponse =  scheduleRepository.findScheduleById(schedule_id);

    if(scheduleResponse.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄이 존재하지 않습니다.");
    }

    return scheduleResponse.get();
  }

  @Transactional
  @Override
  public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
    int updateRow = scheduleRepository.updateSchedule(scheduleUpdateRequestDto);

    if(updateRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄 수정에 실패했습니다.");
    }

    Optional<ScheduleResponseDto> scheduleResponseDto = scheduleRepository.findScheduleById(scheduleUpdateRequestDto.getSchedule_id());

    if(scheduleResponseDto.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄 수정에 실패했습니다.");
    }

    return scheduleResponseDto.get();
  }
}
