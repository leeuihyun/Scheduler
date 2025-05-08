package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleDeleteDto;
import com.hyun.scheduler.domain.dto.ScheduleRequestDto;
import com.hyun.scheduler.domain.dto.ScheduleResponseDto;
import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository{

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public Long saveSchedule(ScheduleRequestDto scheduleRequestDto) {
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    simpleJdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id").usingColumns("schedule_title", "schedule_content", "user_name", "password");

    Map<String, Object> map = new HashMap<>();
    map.put("schedule_title", scheduleRequestDto.getSchedule_title());
    map.put("schedule_content", scheduleRequestDto.getSchedule_content());
    map.put("user_name", scheduleRequestDto.getUser_name());
    map.put("password", scheduleRequestDto.getPassword());

    Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(map));

    return key.longValue();
  }

  @Override
  public Optional<ScheduleResponseDto> findScheduleById(Long schedule_id) {
    List<ScheduleResponseDto> result =  jdbcTemplate.query("select * from schedule where schedule_id = ?", scheduleRowMapper(), schedule_id);
    return result.stream().findAny();
  }

  @Override
  public List<ScheduleResponseDto> findAllSchedules(String user_name, Optional<LocalDate> optionalDate) {
    if(optionalDate.isEmpty()) {
      return jdbcTemplate.query("select * from schedule where user_name = ?", scheduleRowMapper(), user_name);
    }
    return jdbcTemplate.query("select * from schedule where user_name = ? and DATE(updated_at) = ?", scheduleRowMapper(), user_name, optionalDate.get().toString());
  }

  @Override
  public Integer updateSchedule(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
    return jdbcTemplate.update(
        "update schedule set schedule_title=?, schedule_content = ?, user_name = ? where schedule_id = ? and password = ?",
        scheduleUpdateRequestDto.getSchedule_title(), scheduleUpdateRequestDto.getSchedule_content(),
        scheduleUpdateRequestDto.getUser_name(), scheduleUpdateRequestDto.getSchedule_id(), scheduleUpdateRequestDto.getPassword());
  }

  @Override
  public Integer deleteSchedule(ScheduleDeleteDto scheduleDeleteDto) {
    return jdbcTemplate.update("delete from schedule where schedule_id = ? and password = ?",
        scheduleDeleteDto.getSchedule_id(), scheduleDeleteDto.getPassword());
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
