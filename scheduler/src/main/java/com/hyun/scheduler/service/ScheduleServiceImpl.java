package com.hyun.scheduler.service;

import com.hyun.scheduler.domain.dto.ScheduleCreateResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleCreateRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.domain.dto.UserDto;
import com.hyun.scheduler.repository.ScheduleRepository;
import com.hyun.scheduler.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ScheduleServiceImpl implements ScheduleService{

  private final ScheduleRepository scheduleRepository;
  private final UserRepository userRepository;

  public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository) {
    this.scheduleRepository = scheduleRepository;
    this.userRepository = userRepository;
  }

  @Transactional
  @Override
  public ScheduleCreateResponseDto saveSchedule(ScheduleCreateRequestDto scheduleRequestDto) {
    
    Optional<UserDto> user = userRepository.findUserByNameAndPassword(scheduleRequestDto);

    Long user_id;
    
    if(user.isEmpty()) {
      user_id = userRepository.saveUser(scheduleRequestDto.getUserName(), scheduleRequestDto.getUserEmail(), scheduleRequestDto.getUserPassword());
    }else {
      user_id = user.get().getUserId();
    }

    Long schedule_id = scheduleRepository.saveSchedule(scheduleRequestDto.getScheduleTitle(), scheduleRequestDto.getScheduleContent(), user_id);

    return new ScheduleCreateResponseDto(user_id, schedule_id);
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(Long user_id, Optional<LocalDate> optionalDate) {
    return scheduleRepository.findAllSchedules(user_id, optionalDate);
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
    Optional<UserDto> user = userRepository.validUserCredentials(scheduleUpdateRequestDto);

    if(user.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "일치하는 유저정보가 없습니다.");
    }

    int userUpdateRow = userRepository.updateUserName(scheduleUpdateRequestDto);

    if(userUpdateRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "유저 수정에 실패했습니다.");
    }

    int updateRow = scheduleRepository.updateSchedule(scheduleUpdateRequestDto);

    if(updateRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄 수정에 실패했습니다.");
    }

    Optional<ScheduleResponseDto> scheduleResponseDto = scheduleRepository.findScheduleById(scheduleUpdateRequestDto.getScheduleId());

    if(scheduleResponseDto.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄 수정에 실패했습니다.");
    }

    return scheduleResponseDto.get();
  }

  @Override
  public void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto) {
    int deleteRow = scheduleRepository.deleteSchedule(scheduleDeleteDto);

    if(deleteRow == 0) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "스케줄 삭제에 실패했습니다.");
    }
  }

  @Override
  public List<ScheduleResponseDto> findPageSchedules(Integer page, Integer size) {
    return scheduleRepository.findPageSchedules(page, size);
  }
}
