package com.hyun.scheduler.service;

import com.hyun.scheduler.exception.ScheduleException;
import com.hyun.scheduler.domain.dto.ScheduleCreateResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleCreateRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.domain.dto.UserDto;
import com.hyun.scheduler.enums.RequestBodyErrorEnum;
import com.hyun.scheduler.repository.ScheduleRepository;
import com.hyun.scheduler.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
        UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public ScheduleCreateResponseDto saveSchedule(ScheduleCreateRequestDto scheduleRequestDto) {

        Optional<UserDto> user = userRepository.findUserByNameAndPassword(scheduleRequestDto);

        Long userId;

        if (user.isEmpty()) {
            userId = userRepository.saveUser(scheduleRequestDto.getUserName(),
                scheduleRequestDto.getUserEmail(), scheduleRequestDto.getUserPassword());
        } else {
            userId = user.get().getUserId();
        }

        Long scheduleId = scheduleRepository.saveSchedule(scheduleRequestDto.getScheduleTitle(),
            scheduleRequestDto.getScheduleContent(), userId);

        return new ScheduleCreateResponseDto(userId, scheduleId);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules(Long user_id,
        Optional<LocalDate> optionalDate) {
        return scheduleRepository.findAllSchedules(user_id, optionalDate);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long schedule_id) {
        Optional<ScheduleResponseDto> scheduleResponse = scheduleRepository.findScheduleById(
            schedule_id);

        if (scheduleResponse.isEmpty()) {
            throw new ScheduleException(RequestBodyErrorEnum.SCHEDULE_NOT_FOUND);
        }

        return scheduleResponse.get();
    }

    @Transactional
    @Override
    public ScheduleResponseDto updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
        Optional<UserDto> user = userRepository.validUserCredentials(scheduleUpdateRequestDto);

        if (user.isEmpty()) {
            throw new ScheduleException(RequestBodyErrorEnum.PASSWORD_MISMATCH);
        }

        int userUpdateRow = userRepository.updateUserName(scheduleUpdateRequestDto);

        if (userUpdateRow == 0) {
            throw new ScheduleException(RequestBodyErrorEnum.USER_MODIFY_FAIL);
        }

        int updateRow = scheduleRepository.updateSchedule(scheduleUpdateRequestDto);

        Optional<ScheduleResponseDto> scheduleResponseDto = scheduleRepository.findScheduleById(
            scheduleUpdateRequestDto.getScheduleId());

        if (updateRow == 0 || scheduleResponseDto.isEmpty()) {
            throw new ScheduleException(RequestBodyErrorEnum.SCHEDULE_NOT_FOUND);
        }

        return scheduleResponseDto.get();
    }

    @Override
    public void deleteSchedule(ScheduleDeleteDto scheduleDeleteDto) {
        Optional<UserDto> user = userRepository.validUserCredentials(scheduleDeleteDto);

        if (user.isEmpty()) {
            throw new ScheduleException(RequestBodyErrorEnum.PASSWORD_MISMATCH);
        }

        int deleteRow = scheduleRepository.deleteSchedule(scheduleDeleteDto);

        if (deleteRow == 0) {
            throw new ScheduleException(RequestBodyErrorEnum.SCHEDULE_NOT_FOUND);
        }
    }

    @Override
    public List<ScheduleResponseDto> findPageSchedules(Integer page, Integer size) {
        return scheduleRepository.findPageSchedules(page, size);
    }
}
