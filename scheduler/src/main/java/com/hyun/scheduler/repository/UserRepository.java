package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.domain.dto.UserDto;
import com.hyun.scheduler.domain.model.UserValidCredentials;
import com.hyun.scheduler.domain.model.UserValidNameAndPassword;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    <T extends UserValidNameAndPassword> Optional<UserDto> findUserByNameAndPassword(T dto);

    Optional<String> findUserPasswordById(Long userId);

    List<String> findUserPasswordByName(String userName);

    List<UserDto> findUserByName(String userName);

    Integer updateUserName(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

    Long saveUser(String userName, String userEmail, String userPassword);
}
