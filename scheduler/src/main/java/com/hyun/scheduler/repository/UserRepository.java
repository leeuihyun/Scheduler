package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.domain.dto.UserDto;
import com.hyun.scheduler.domain.dto.UserValidCredentials;
import com.hyun.scheduler.domain.dto.UserValidNameAndPassword;
import java.util.Optional;

public interface UserRepository {
  <T extends UserValidNameAndPassword> Optional<UserDto> findUserByNameAndPassword(T dto);

  <T extends UserValidCredentials>Optional<UserDto> validUserCredentials(T dto);

  Integer updateUserName(ScheduleUpdateRequestDto scheduleUpdateRequestDto);

  Long saveUser(String userName, String userEmail, String userPassword);
}
