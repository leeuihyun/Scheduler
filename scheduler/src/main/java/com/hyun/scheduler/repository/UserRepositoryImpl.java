package com.hyun.scheduler.repository;

import com.hyun.scheduler.domain.dto.ScheduleUpdateRequestDto;
import com.hyun.scheduler.domain.dto.UserDto;
import com.hyun.scheduler.domain.dto.UserValidCredentials;
import com.hyun.scheduler.domain.dto.UserValidNameAndPassword;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class UserRepositoryImpl implements UserRepository{

  private final JdbcTemplate jdbcTemplate;

  public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public <T extends UserValidNameAndPassword> Optional<UserDto> findUserByNameAndPassword(T dto) {
    List<UserDto> result =  jdbcTemplate.query("select * from user where user_name = ? and user_password = ?"
        , userRowMapper(), dto.getUserName(), dto.getUserPassword());
    return result.stream().findAny();
  }

  @Override
  public <T extends UserValidCredentials> Optional<UserDto> validUserCredentials(T dto) {
    List<UserDto> result =  jdbcTemplate.query("select * from user where user_id = ? and user_password = ?"
        , userRowMapper(), dto.getUserId(), dto.getUserPassword());
    return result.stream().findAny();
  }


  @Override
  public Integer updateUserName(ScheduleUpdateRequestDto scheduleUpdateRequestDto) {
    return jdbcTemplate.update(
        "update user set user_name=? where user_id = ? and user_password = ?",
        scheduleUpdateRequestDto.getUserName(), scheduleUpdateRequestDto.getUserId(), scheduleUpdateRequestDto.getUserPassword());
  }

  @Override
  public Long saveUser(String userName, String userEmail, String userPassword) {
    SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

    simpleJdbcInsert
        .withTableName("user")
        .usingGeneratedKeyColumns("user_id")
        .usingColumns("user_name", "user_email", "user_password");

    Map<String, Object> map = new HashMap<>();
    map.put("user_email", userEmail);
    map.put("user_name", userName);
    map.put("user_password", userPassword);

    Number key = simpleJdbcInsert.executeAndReturnKey(new MapSqlParameterSource(map));

    return key.longValue();
  }

  private RowMapper<UserDto> userRowMapper() {
    return new RowMapper() {
      @Override
      public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserDto(
            rs.getLong("user_id"),
            rs.getString("user_email"),
            rs.getString("user_name"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at").toLocalDateTime()
        );
      }
    };
  }
}
