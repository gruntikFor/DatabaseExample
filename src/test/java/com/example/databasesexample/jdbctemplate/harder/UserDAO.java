package com.example.databasesexample.jdbctemplate.harder;

import com.example.databasesexample.jdbctemplate.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAO {

    void setDataSource(DataSource ds);

    List<User> listUsers();

    List<User> listUsersExtractor();

    User getUser(final Integer id);

    void updateDescription(Integer id, String description);

    void create(int id, String name, String surname, int age, String description);

}
