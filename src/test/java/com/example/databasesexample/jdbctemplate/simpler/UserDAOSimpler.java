package com.example.databasesexample.jdbctemplate.simpler;

import com.example.databasesexample.jdbctemplate.User;

import javax.sql.DataSource;
import java.util.List;

public interface UserDAOSimpler {

    void setDataSource(DataSource ds);

    User getUser(final Integer id);

    List<User> getUsersListSimpler();

    void createSimpler(int id, String name, String surname, int age, String description);

    User getUserSimpler(final Integer id);

    void updateSimpler(int id, String name, String surname, int age, String description);

    void deleteUser(final Integer id);


}
