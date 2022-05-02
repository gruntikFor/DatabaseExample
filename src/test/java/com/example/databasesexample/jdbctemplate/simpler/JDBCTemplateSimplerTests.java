package com.example.databasesexample.jdbctemplate.simpler;

import com.example.databasesexample.jdbctemplate.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JDBCTemplateSimplerTests {

    @Autowired
    UserJDBCTemplateSimpler jdbcTemplate;

    @Test
    public void addRowSimpler() {
        jdbcTemplate.createSimpler(4, "liz", "shp", 23, "ya liza");
    }

    @Test
    public void getUserSimpler() {
        User user = jdbcTemplate.getUserSimpler(1);
        System.out.println(user);
        Assertions.assertEquals(1, user.getId());
    }

    @Test
    public void updateSimpler() {
        User user = jdbcTemplate.getUserSimpler(1);
        user.setName("IIIIIgor");

        jdbcTemplate.updateSimpler(user.getId(), user.getName(), user.getSurname(), user.getAge(), user.getDescription());
        Assertions.assertEquals(user.getName(), jdbcTemplate.getUserSimpler(1).getName());
    }

    @Test
    public void deleteUser() {
        jdbcTemplate.deleteUser(2);
        Assertions.assertEquals(3, jdbcTemplate.getUsersListSimpler().size());
    }

    @Test
    public void usersListSimpler() {
        System.out.println(jdbcTemplate.getUsersListSimpler());
    }

}
