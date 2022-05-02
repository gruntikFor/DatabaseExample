package com.example.databasesexample.jdbctemplate;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Scope("singleton")
public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .surname(rs.getString("surname"))
                .age(rs.getInt("age"))
                .description(rs.getString("description"))
                .build();
    }
}
