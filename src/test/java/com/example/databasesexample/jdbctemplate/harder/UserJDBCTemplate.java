package com.example.databasesexample.jdbctemplate.harder;

import com.example.databasesexample.jdbctemplate.User;
import com.example.databasesexample.jdbctemplate.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserJDBCTemplate implements UserDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    UserMapper userMapper;

    @Override
    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("users");
    }

    @Override
    public List<User> listUsers() {
        String SQL = "select * from users";
        return jdbcTemplate.query(SQL, userMapper);
    }

    @Override
    public List<User> listUsersExtractor() {
        String SQL = "select * from users";
        List<User> users = jdbcTemplate.query(SQL, new ResultSetExtractor<List<User>>() {
            @Override
            public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {

                List<User> list = new ArrayList<>();
                while (rs.next()) {
                    User user = User.builder()
                            .id(rs.getInt("id"))
                            .name(rs.getString("name"))
                            .surname(rs.getString("surname")).build();
                    list.add(user);
                }
                return list;
            }
        });
        return users;
    }

    @Override
    public User getUser(final Integer id) {
        String SQL = "select * from users where id = ?";
        List<User> users = jdbcTemplate.query(SQL, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        }, new UserMapper());

        return users.get(0);
    }

    @Override
    public void updateDescription(Integer id, String description) {
        MapSqlParameterSource in = new MapSqlParameterSource();
        in.addValue("id", id);
        in.addValue("description", new SqlLobValue(description, new DefaultLobHandler()), Types.CLOB);

        String SQL = "update users set description = :description where id = :id";
        namedJdbcTemplate.update(SQL, in);
        System.out.println("update description id: " + id);
    }

    @Override
    public void create(int id, String name, String surname, int age, String description) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", name);
        map.put("surname", surname);
        map.put("age", age);
        map.put("description", description);
        simpleJdbcInsert.execute(map);
        System.out.println("Add row: " + id + " " + name + " " + surname + " " + age + " " + description);
    }

}
