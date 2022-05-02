package com.example.databasesexample.jdbctemplate.harder;

import com.example.databasesexample.jdbctemplate.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JDBCTemplateTests {

    @Autowired
    UserJDBCTemplate userJDBCTemplate;

    @Test
    public void addRow() {
        userJDBCTemplate.create(3, "max", "maz", 24, "description");
    }

    @Test
    public void updateDescription() {
        userJDBCTemplate.updateDescription(1, "hello description 1");
        Assertions.assertEquals("hello description 1", userJDBCTemplate.getUser(1).getDescription());
    }

    @Test
    public void usersList() {
        List<User> users = userJDBCTemplate.listUsers();
        Assertions.assertEquals(3, users.size());
    }

    @Test
    public void usersListExtract() {
        List<User> users = userJDBCTemplate.listUsersExtractor();
        Assertions.assertEquals(2, users.size());
    }

    @Test
    public void getUser() {
        User user = userJDBCTemplate.getUser(1);
        System.out.println(user);
        Assertions.assertEquals(1, user.getId());
    }


}
