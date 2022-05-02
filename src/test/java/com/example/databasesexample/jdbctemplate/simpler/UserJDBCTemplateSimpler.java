package com.example.databasesexample.jdbctemplate.simpler;

import com.example.databasesexample.jdbctemplate.User;
import com.example.databasesexample.jdbctemplate.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class UserJDBCTemplateSimpler implements UserDAOSimpler {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Override
    @Autowired
    public void setDataSource(DataSource ds) {
        this.dataSource = ds;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User getUser(Integer id) {
        String SQL = "select * from users where id = ?";
        return jdbcTemplate.query(SQL, new UserMapper(), id).get(0);
    }

    @Override
    public void createSimpler(int id, String name, String surname, int age, String description) {
        String SQL = "insert into users (id, name, surname, age, description) values(?,?,?,?,?)";
        jdbcTemplate.update(SQL, id, name, surname, age, description);
        System.out.println("createSimpler");
    }

    @Override
    public List<User> getUsersListSimpler() {
        String SQL = "select * from users";
        return jdbcTemplate.query(SQL, new UserMapper());
    }

    @Override
    public User getUserSimpler(Integer id) {
        String SQL = "select * from users where id = ?";
        return jdbcTemplate.query(SQL, new UserMapper(), id).get(0);
    }

    @Override
    public void updateSimpler(int id, String name, String surname, int age, String description) {
        String SQL = "update users set name = ?, surname = ?, age = ?, description = ? where id = ?";
        jdbcTemplate.update(SQL, name, surname, age, description, id);
    }

    @Override
    public void deleteUser(Integer id) {
        String SQL = "delete from users where id = ?";
        jdbcTemplate.update(SQL, id);
        System.out.println("delete user: " + id);
    }

}
