package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleCreateRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.domain.dto.UserDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

  private final JdbcTemplate jdbcTemplate;

  public ScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Long saveSchedule(String scheduleTitle, String scheduleContent, Long userId) {
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

    simpleJdbcInsert
        .withTableName("schedule")
        .usingGeneratedKeyColumns("schedule_id")
        .usingColumns("schedule_title", "schedule_content", "user_id");

    Map<String, Object> map = new HashMap<>();
    map.put("schedule_title", scheduleTitle);
    map.put("schedule_content", scheduleContent);
    map.put("user_id", userId);

    Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(map));

    return key.longValue();
  }

  @Override
  public Optional<ScheduleResponseDto> findScheduleById(Long scheduleId) {
    List<ScheduleResponseDto> result = jdbcTemplate.query(
        "select s.schedule_id, s.schedule_title, s.schedule_content, u.user_name, s.created_at, s.updated_at from schedule s join user u on s.user_id = u.user_id where schedule_id = ?",
        scheduleRowMapper(), scheduleId);
    return result.stream().findAny();
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(Long userId, Optional<LocalDate> optionalDate) {
    if(optionalDate.isEmpty()) {
      return jdbcTemplate.query("select s.schedule_id, s.schedule_title, s.schedule_content, u.user_name, s.created_at, s.updated_at from schedule s join user u on s.user_id = u.user_id where s.user_id = ?", scheduleRowMapper(), userId);
    }
    return jdbcTemplate.query("select s.schedule_id, s.schedule_title, s.schedule_content, u.user_name, s.created_at, s.updated_at from schedule s join user u on s.user_id = u.user_id where s.user_id = ? and DATE(s.updated_at) = ?", scheduleRowMapper(), userId, optionalDate.get().toString());
  }

  @Override
  public Integer updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
    return jdbcTemplate.update(
        "update schedule set schedule_title=?, schedule_content = ? where schedule_id = ?",
        scheduleUpdateRequestDto.getScheduleTitle(), scheduleUpdateRequestDto.getScheduleContent(),
        scheduleUpdateRequestDto.getScheduleId());
  }

  @Override
  public Integer deleteSchedule(ScheduleDeleteDto scheduleDeleteDto) {
    return jdbcTemplate.update("delete s from schedule s join user u on s.user_id = u.user_id where s.schedule_id = ? and u.user_password = ?",
        scheduleDeleteDto.getScheduleId(), scheduleDeleteDto.getUserPassword());
  }

  private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
    return new RowMapper<ScheduleResponseDto>() {
      @Override
      public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ScheduleResponseDto(
            rs.getLong("schedule_id"),
            rs.getString("schedule_title"),
            rs.getString("schedule_content"),
            rs.getString("user_name"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
      }
    };
  }
}
