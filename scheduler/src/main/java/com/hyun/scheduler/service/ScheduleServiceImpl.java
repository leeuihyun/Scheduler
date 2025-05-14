package com.hyun.scheduler.service;

import com.hyun.scheduler.domain.model.UserValidCredentials;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository,
        UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public ScheduleCreateResponseDto saveSchedule(ScheduleCreateRequestDto scheduleRequestDto) {

        Long userId = null;

        ScheduleCreateRequestDto encodedRequest =
            scheduleRequestDto.EndcodePassword(passwordEncoder.encode(scheduleRequestDto.getUserPassword()));

        List<UserDto> userList = userRepository.findUserByName(scheduleRequestDto.getUserName());

        if(!userList.isEmpty()) {
            for(UserDto user : userList) {
                Optional<String> userOptionalPassword= userRepository.findUserPasswordById(user.getUserId());
                if(userOptionalPassword.isPresent()){
                    if(passwordEncoder.matches(scheduleRequestDto.getUserPassword(), userOptionalPassword.get())) {
                        userId = user.getUserId();
                        break;
                    }
                }
            }
        }

        if(userList.isEmpty() || userId == null) {
            userId = userRepository.saveUser(encodedRequest.getUserName(),
                encodedRequest.getUserEmail(), encodedRequest.getUserPassword());
        }

        Long scheduleId = scheduleRepository.saveSchedule(encodedRequest.getScheduleTitle(),
            encodedRequest.getScheduleContent(), userId);

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

        int userUpdateRow = userRepository.updateUserName(scheduleUpdateRequestDto);

        if (userUpdateRow == 0) {
            throw new ScheduleException(RequestBodyErrorEnum.USER_ID_MISMATCH);
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
        int deleteRow = scheduleRepository.deleteSchedule(scheduleDeleteDto);

        if (deleteRow == 0) {
            throw new ScheduleException(RequestBodyErrorEnum.SCHEDULE_NOT_FOUND);
        }
    }

    @Override
    public List<ScheduleResponseDto> findPageSchedules(Integer page, Integer size) {
        return scheduleRepository.findPageSchedules(page, size);
    }

    @Override
    public <T extends UserValidCredentials> void userValid(T dto) {
        Optional<String> userPassword = userRepository.findUserPasswordById(dto.getUserId());

        if(userPassword.isEmpty()) {
            throw new ScheduleException(RequestBodyErrorEnum.USER_ID_MISMATCH);
        }
        if (!passwordEncoder.matches(dto.getUserPassword(), userPassword.get())) {
            throw new ScheduleException(RequestBodyErrorEnum.PASSWORD_MISMATCH);
        }
    }
}
