package com.hyun.scheduler.controller;

import com.hyun.scheduler.domain.dto.ScheduleCreateResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleCreateRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.service.ScheduleService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @PostMapping("/schedule")
  public ResponseEntity<ScheduleCreateResponseDto> createSchedule(@RequestBody ScheduleCreateRequestDto scheduleRequestDto) {
    ScheduleCreateResponseDto scheduleCreateResponseDto =  scheduleService.saveSchedule(scheduleRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(scheduleCreateResponseDto);
  }

  @GetMapping("/schedules")
  public ResponseEntity<List<ScheduleResponseDto>> findAllSchedules(@RequestParam Long userId, @RequestParam(required = false) LocalDate date) {
    Optional<LocalDate> optionalDate = date != null ? Optional.of(date) : Optional.empty();

    List<ScheduleResponseDto> scheduleResponseList = scheduleService.findAllSchedules(userId, optionalDate);
    return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseList);
  }

  @GetMapping("/schedule/{scheduleId}")
  public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long scheduleId) {
    ScheduleResponseDto scheduleResponse = scheduleService.findScheduleById(scheduleId);
    return ResponseEntity.status(HttpStatus.OK).body(scheduleResponse);
  }

  @PutMapping("/schedule/update")
  public ResponseEntity<ScheduleResponseDto> updateSchedule(@RequestBody ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
    ScheduleResponseDto scheduleResponseDto = scheduleService.updateSchedule(scheduleUpdateRequestDto);
    return ResponseEntity.status(HttpStatus.OK).body(scheduleResponseDto);
  }

  @PostMapping("/schedule/delete")
  public ResponseEntity<Void> deleteSchedule(@RequestBody ScheduleDeleteDto scheduleDeleteDto) {
    scheduleService.deleteSchedule(scheduleDeleteDto);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping("/page/schedules")
  public ResponseEntity<List<ScheduleResponseDto>> findPageSchedules(
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size)
  {
    List<ScheduleResponseDto> pageScheduleList = scheduleService.findPageSchedules(page,size);
    return ResponseEntity.status(HttpStatus.OK).body(pageScheduleList);
  }
}
